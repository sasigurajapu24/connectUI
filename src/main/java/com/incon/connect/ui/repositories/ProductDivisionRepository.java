package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;

public interface ProductDivisionRepository extends JpaRepository<ProductDivision, Long> {

	ProductDivision findById(Long id) throws DataAccessException;

	List<ProductDivision> findByCategoryId(Long Id);

	ProductDivision findByName(String name);
	

}
