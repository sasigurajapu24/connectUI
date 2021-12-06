package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.Brand;

public interface BrandsRepository extends JpaRepository<Brand, Long> {

	List<Brand> findByDivisionId(Long Id);

	Brand findByName(String name);
	

}
