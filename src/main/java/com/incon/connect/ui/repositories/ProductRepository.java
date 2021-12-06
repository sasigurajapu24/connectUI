package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.incon.connect.ui.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findById(Long id) throws DataAccessException;

	List<Product> findByCreatedByOrderByCreatedDateDesc(Long createdBy) throws DataAccessException;
	
	Page<Product> findByCreatedBy(Long createdBy, Pageable pageable) throws DataAccessException;

	List<Product> findByNameLikeIgnoreCase(String name) throws DataAccessException;
	
	Product findByNameIgnoreCase(String name) throws DataAccessException;
	
	Product findByModelNumberIgnoreCase(String modelNumber);
	
	@Query("SELECT COUNT(u) FROM Product u WHERE u.createdBy=?1")
    Long countByCreatedBy(Long id);	
	
	Page<Product> findByEntryPoint(Integer entryPoint, Pageable pageable);
}
