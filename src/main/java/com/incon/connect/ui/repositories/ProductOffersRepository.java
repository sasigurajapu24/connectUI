package com.incon.connect.ui.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.ProductOffers;

public interface ProductOffersRepository extends JpaRepository<ProductOffers, Long> {

	ProductOffers findById(Long id) throws DataAccessException;
	
	ProductOffers findByProductId(Long id) throws DataAccessException;
}
