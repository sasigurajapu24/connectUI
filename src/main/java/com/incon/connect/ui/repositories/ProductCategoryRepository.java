package com.incon.connect.ui.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	ProductCategory findById(Long id) throws DataAccessException;
	
	ProductCategory findByName(String categoryName);

}
