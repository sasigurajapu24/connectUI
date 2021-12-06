package com.incon.connect.ui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.CodeRequest;

public interface CodeRequestRepository extends JpaRepository<CodeRequest, Long>{
	
	CodeRequest findByCodeId(Long id);
	

}
