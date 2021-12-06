package com.incon.connect.ui.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.QRCodes;
import com.incon.connect.ui.entities.TrueCheckCodes;

public interface UniqueCodesRepository extends JpaRepository<QRCodes, Long>{
	
	/*@Query("select u from UniqueCodes u where u.chars = :chars or u.digits = :digits")
	UniqueCodes findByCharsAndDigits(@Param("chars") String chars,
	                                 @Param("digits") String digits);
	*/
//	Page<QRCodes> findByProduct(Product product, Pageable pageable);

	Page<QRCodes> findByCreatedBy(Long id, Pageable pageable);
}
