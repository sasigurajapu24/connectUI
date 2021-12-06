/**
 * 
 */
package com.incon.connect.ui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.Offers;

/**
 * @author bogavalli.srinivas
 *
 */
public interface OffersRepository extends JpaRepository<Offers, Long>{

//	List<Offers> findByNameLikeIgnoreCase(String query);
	
}
