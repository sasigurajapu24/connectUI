/**
 * 
 */
package com.incon.connect.ui.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incon.connect.ui.entities.AppHits;

/**
 * @author bogavalli.srinivas
 *
 */
public interface AppHitsRepository extends JpaRepository<AppHits, Long>{
	
	AppHits findById(Long code);
	
	@Query("select count(e) from AppHits e where e.msisdn = ?1 and e.product.id = ?2")
	Integer countByMsisdnAndProductId(String msisdn, Long productId);
	
	Page<AppHits> findByProductId(Long id, Pageable pageable);
	 
	
}
