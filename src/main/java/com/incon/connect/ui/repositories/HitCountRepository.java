/**
 * 
 */
package com.incon.connect.ui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.incon.connect.ui.entities.HitCount;

/**
 * @author bogavalli.srinivas
 *
 */
public interface HitCountRepository extends JpaRepository<HitCount, Long>{
	
//	 @Query("select u from Crew u where u.driverMasterId=?")
	HitCount findById(Long code);
	 
	
}
