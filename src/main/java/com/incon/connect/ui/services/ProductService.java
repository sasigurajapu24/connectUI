package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.Brand;
import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;
import com.incon.connect.ui.entities.ProductOffers;
import com.incon.connect.ui.exceptions.ConnectCustomException;

public interface ProductService {
	
	Product findById(Long code);
	
	Page<Product> findByUserId(String code, int page, int size);
	
	List<Product> fetchAllProducts();
	
	Product addProduct(Product Products) throws Exception;
	
	Product updateProduct(Product Products);
	
	List<Product> searchProducts(ProductDto product, int page, int size);
	
//	List<Company> fetchAllCompanies();

	Product findByName(String name);

	Page<Product> fetchUserProducts(Integer page, Integer size);

	Page<Product> fetchAllProducts(int page, int size, Boolean isComponent);

	List<ProductCategory> fetchCategories();

	List<ProductDivision> fetchTypes(Long categoryId);

	byte[] fetchLogo(Long productId, Integer i);

	ProductOffers addOffer(ProductOffers offer);

	Product checkUnique(String name, String type);

	List<Brand> fetchBrands(Long divisionId);

	Page<Product> findByEntryPoint(int page, int size, Integer entryPoint);

}
