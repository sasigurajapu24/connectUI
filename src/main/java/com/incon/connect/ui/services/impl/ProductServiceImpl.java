package com.incon.connect.ui.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.Brand;
import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;
import com.incon.connect.ui.entities.ProductOffers;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.exceptions.ConnectCustomException;
import com.incon.connect.ui.repositories.BrandsRepository;
import com.incon.connect.ui.repositories.ProductCategoryRepository;
import com.incon.connect.ui.repositories.ProductDivisionRepository;
import com.incon.connect.ui.repositories.ProductOffersRepository;
import com.incon.connect.ui.repositories.ProductRepository;
import com.incon.connect.ui.services.DataService;
import com.incon.connect.ui.services.ProductService;
import com.incon.connect.ui.util.MyUtils;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductDivisionRepository productTypeRepository;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	ProductOffersRepository productOffersRepository;
	
	@Autowired
	BrandsRepository brandsRepository;

	@Autowired
	DataService dataService;

	@Override
	public Product findById(Long code) {
		return productRepository.findOne(code);
	}

	@Override
	public Page<Product> findByUserId(String code, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		return productRepository.findByCreatedBy(Long.parseLong(code), pageable);
	}

	@Override
	public List<Product> fetchAllProducts() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Product> products = null;
		if (null != user) {
			products = productRepository.findByCreatedByOrderByCreatedDateDesc(user.getId());
		} else {
			products = productRepository.findAll();
		}
		if (null != products)
			logger.info("Products count : " + products.size());
		return products;
	}

	@Override
	public Page<Product> fetchAllProducts(int page, int size, Boolean isComponent) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		Page<Product> result = null;
		if (isComponent) {
			result = new PageImpl<>(productRepository.findAll());
		} else {
			result = productRepository.findAll(pageable);
		}
		return result;
	}

	@Override
	public Product addProduct(Product products) throws Exception {
		logger.info("Adding products[{}]", products.getId());
		// try {
		// products.setLogo(products.getFile().getBytes());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// Company company = companyRepository.findById(
		// MyUtils.getSessionUser().getCompanyId());
		// products.setCompany(company);
		products.setEntryPoint(1);
		products.setCreatedBy(MyUtils.getSessionUser().getId());
		Product product = productRepository.save(products);
		return product;
	}

	@Override
	public Product updateProduct(Product products) {
		logger.info("Update products[{}]", products.getId());
		try {
			Product update = findById(products.getId());
			if (null != products.getFile() && products.getFile().getSize() > 0) {
					// products.setLogo();
			} else {
				products.setLogo(update.getLogo());
			}
			
			if (null != products.getImage() && products.getImage().length > 0) {
				// products.setLogo();
			} else {
				products.setImage(update.getImage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productRepository.save(products);
	}

	@Override
	public List<Product> searchProducts(ProductDto product, int page, int size) {
		logger.info("Searching product.search");
		// Pageable pageable = new PageRequest(page, size, new
		// Sort(Direction.DESC, "createdDate"));
		List<Product> results = productRepository.findByNameLikeIgnoreCase(product.getQuery());
		return results;
	}

	@Override
	public Product findByName(String name) {
		return productRepository.findByNameIgnoreCase(name);
	}

	@Override
	public Page<Product> fetchUserProducts(Integer page, Integer size) {
		logger.debug("Fetch USer products");

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Page<Product> products = null;
		if (null != user && ( new Long(6l).equals(user.getUserType().getId()) || new Long(2l).equals(user.getUserType().getId()))) {
			if (null == page && null == size) {
				List<Product> productList = productRepository.findByCreatedByOrderByCreatedDateDesc(user.getId());
				products = new PageImpl<>(productList);
			} else {
				Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
				products = productRepository.findByCreatedBy(user.getId(), pageable);
			}
		} else {
			Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
			products = productRepository.findAll(pageable);
		}
		if (null != products)
			logger.info("Products count : " + products.getContent().size());
		return products;
	}

	@Override
	public List<ProductCategory> fetchCategories() {
		return productCategoryRepository.findAll();
	}

	@Override
	public List<ProductDivision> fetchTypes(Long categoryId) {
		List<ProductDivision> typesList = new ArrayList<>();
		if (null != categoryId) {
			typesList = productTypeRepository.findByCategoryId(categoryId);
		}
		return typesList;
	}

	@Override
	public byte[] fetchLogo(Long productId, Integer type) {
		byte[] returnValue = null;

		Product pr = productRepository.findOne(productId);
		if (null != pr) {
			if (1 == type) {
				returnValue = pr.getLogo();
			} else {
				// returnValue = pr.getImageBlob();
			}
		}
		return returnValue;
	}

	@Override
	public ProductOffers addOffer(ProductOffers offer) {
		// TODO Auto-generated method stub
		logger.info("Adding Product Offer[{}]", offer.getId());
		ProductOffers existing = productOffersRepository.findByProductId(offer.getProductId());
		if (null != existing) {
			existing.setName(offer.getName());
			existing.setDescription(offer.getDescription());
			existing.setStartDate(offer.getStartDate());
			existing.setEndDate(offer.getEndDate());
			existing = productOffersRepository.save(existing);
		} else {
			existing = productOffersRepository.save(offer);
		}

		if (existing != null) {

		}
		return existing;
	}

	@Override
	public Product checkUnique(String name, String type) {
		if ("name".equals(type)) {
			return productRepository.findByNameIgnoreCase(name);
		} else {
			return productRepository.findByModelNumberIgnoreCase(name);
		}
	}

	@Override
	public List<Brand> fetchBrands(Long divisionId) {
		return brandsRepository.findByDivisionId(divisionId);
	}

	@Override
	public Page<Product> findByEntryPoint(int page, int size,Integer entryPoint) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		return productRepository.findByEntryPoint(entryPoint, pageable);
	}

}
