/**
 * 
 */
package com.incon.connect.ui.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incon.connect.ui.entities.Notifications;
import com.incon.connect.ui.entities.Product;

/**
 * @author bogavalli.srinivas
 *
 */
public interface NotificationsRepository extends JpaRepository<Notifications, Long>{
	
//	@Query(value = "SELECT n FROM Notifications n WHERE n.productId = ?1 AND n.fromDate <= ?2 AND n.toDate >= ?2")
//	List<Notifications> findbyProductIdAndDateBetweenFromDateAndToDate(Long id, Date date);
//
//	@Query(value = "SELECT n FROM Notifications n WHERE n.fromDate <= ?1 AND n.toDate >= ?1")
//	List<Notifications> findbyDateBetweenFromDateAndToDate(Date time);
//	
////	@Query(value = "SELECT n FROM Notifications n WHERE n.product.name like '%?1%'")
////	Page<Notifications> findbyProductLike( String productName, Pageable pageable);
//	
//	@Query(value = "SELECT n FROM Notifications n WHERE n.product in (?1)")
//	Page<Notifications> findbyProductIn( List<Product> products, Pageable pageable);
//
//	@Query(value = "SELECT n FROM Notifications n WHERE n.createdBy = ?1")
//	Page<Notifications> findbyCreatedBy(Long id, Pageable pageable);
	
}
