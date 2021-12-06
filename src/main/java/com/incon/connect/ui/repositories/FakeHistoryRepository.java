/**
 * 
 */
package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.incon.connect.ui.entities.FakeHistory;

/**
 * @author bogavalli.srinivas
 *
 */
public interface FakeHistoryRepository extends JpaRepository<FakeHistory, Long>{
	
//	 @Query("select u from Crew u where u.driverMasterId=?")
	FakeHistory findById(Long code);
	
	
	List<FakeHistory> findByMsisdn(String msisdn);
	 
	
}
