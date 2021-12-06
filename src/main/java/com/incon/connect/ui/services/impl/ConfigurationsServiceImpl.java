package com.incon.connect.ui.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.entities.Brand;
import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;
import com.incon.connect.ui.exceptions.ConnectCustomException;
import com.incon.connect.ui.repositories.BrandsRepository;
import com.incon.connect.ui.repositories.ProductCategoryRepository;
import com.incon.connect.ui.repositories.ProductDivisionRepository;
import com.incon.connect.ui.services.ConfigurationService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ConfigurationsServiceImpl implements ConfigurationService {
	
	@Autowired
	ProductDivisionRepository productTypeRepository;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	BrandsRepository brandsRepository;

	@Override
	public Page<ProductCategory> getCategories(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "insertTimestamp"));
		return productCategoryRepository.findAll(pageable);
	}

	@Override
	public Page<ProductDivision> getDivisions(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "insertTimestamp"));
		return productTypeRepository.findAll(pageable);
	}

	@Override
	public Page<Brand> getBrands(Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "insertTimestamp"));
		return brandsRepository.findAll(pageable);
	}
	
	@Override
	public ProductCategory addCategory(ProductCategory productCategory) throws ConnectCustomException {
		ProductCategory category = productCategoryRepository.findByName(productCategory.getName());
		if(null != category){
			throw new ConnectCustomException("Category already available.");
		}
		
		category = productCategoryRepository.save(productCategory);
		return category;
	}
	
	@Override
	public ProductDivision addDivision(ProductDivision productDivision) throws ConnectCustomException {
		ProductDivision division = productTypeRepository.findByName(productDivision.getName());
		if(null != division){
			throw new ConnectCustomException("Division already available.");
		}
		
		division = productTypeRepository.save(productDivision);
		return division;
	}
	
	@Override
	public Brand addBrand(Brand brand) throws ConnectCustomException {
		Brand division = brandsRepository.findByName(brand.getName());
		if(null != division){
			throw new ConnectCustomException("Division already available.");
		}
		
		division = brandsRepository.save(brand);
		return division;
	}

	
}
