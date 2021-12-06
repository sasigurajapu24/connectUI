/**
 * 
 */
package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incon.connect.ui.entities.MenuOption;

/**
 * @author bogavalli.srinivas
 *
 */
public interface MenuOptionsRepository extends JpaRepository<MenuOption, Long>{

//	List<Offers> findByNameLikeIgnoreCase(String query);
	
	List<MenuOption> findByBrandId(Long brandId);
}
