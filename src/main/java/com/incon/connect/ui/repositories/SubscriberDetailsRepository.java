package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.SubscriberDetails;

public interface SubscriberDetailsRepository extends JpaRepository<SubscriberDetails, Long>{
	//List<Depot> findBydepotCode(String depotCode);
	
	SubscriberDetails findByMsisdn(String msisdn);
	
	SubscriberDetails findByMsisdnAndOtp(String msisdn, String otp);

	Page<SubscriberDetails> findByUsertype(Integer i, Pageable pageable);
	
	List<SubscriberDetails> findByUsertypeAndApproveStatus(Integer i, Integer approveStatus);
}
