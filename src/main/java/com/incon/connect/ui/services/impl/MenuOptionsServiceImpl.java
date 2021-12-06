package com.incon.connect.ui.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.MenuOption;
import com.incon.connect.ui.repositories.MenuOptionsRepository;
import com.incon.connect.ui.services.MenuOptionsService;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MenuOptionsServiceImpl implements MenuOptionsService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private MenuOptionsRepository menuOptionRepository;

	@Override
	public Page<MenuOption> fetchAllMenuOptions(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		Page<MenuOption> result = menuOptionRepository.findAll(pageable); 
		return result;
	}

	@Override
	public MenuOption addMenuOption(MenuOption offer) {
		logger.info("Adding products[{}]", offer.getId());
//		offer.setCreatedBy(MyUtils.getSessionUser().getId());
		return menuOptionRepository.save(offer);
	}

	@Override
	public MenuOption updateMenuOption(MenuOption offer) {
		logger.info("Update offers[{}]",  offer.getId());
		try {
//			if (null != offer.getImage()) {
//				//products.setLogo();
//			} else {
//				MenuOption update = offersRepository.findOne(offer.getId());
//				offer.setImage(update.getImage());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuOptionRepository.save(offer);
	}

	@Override
	public void deleteMenuOption (MenuOption code) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MenuOption> searchMenuOptions(ProductDto product, int page, int size) {
		logger.info("Searching product.search");
//			Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		List<MenuOption> results = null ;//offersRepository.findByNameLikeIgnoreCase(product.getQuery());
		return results;
	}

	@Override
	public List<MenuOption> fetchParents(String string, Long brandId) {
		return menuOptionRepository.findByBrandId(brandId);
	}

}
