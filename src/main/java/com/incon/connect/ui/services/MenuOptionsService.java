package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.MenuOption;

public interface MenuOptionsService {

	Page<MenuOption> fetchAllMenuOptions(int page, int size);

	MenuOption addMenuOption(MenuOption menuOption);

	MenuOption updateMenuOption(MenuOption menuOption);

	void deleteMenuOption(MenuOption code);

	List<MenuOption> searchMenuOptions(ProductDto product, int page, int size);

	List<MenuOption> fetchParents(String string, Long brandId);
	
	

}
