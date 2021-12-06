package com.incon.connect.ui.services;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.entities.Brand;
import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;
import com.incon.connect.ui.exceptions.ConnectCustomException;

public interface ConfigurationService {
	
	Page<ProductCategory> getCategories(Integer page, Integer size);
	
	Page<ProductDivision> getDivisions(Integer page, Integer size);
	
	Page<Brand> getBrands(Integer page, Integer size);

	ProductCategory addCategory(ProductCategory productCategory) throws ConnectCustomException;

	ProductDivision addDivision(ProductDivision productDivision) throws ConnectCustomException;

	Brand addBrand(Brand brand) throws ConnectCustomException;

}
