package com.incon.connect.ui.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.MenuOption;
import com.incon.connect.ui.services.MenuOptionsService;
import com.incon.connect.ui.services.impl.CodesServiceImpl;

@Controller
@RequestMapping("/menuoptions")
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(CodesServiceImpl.class);

	
	@Autowired
	private MenuOptionsService offersService;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Page<MenuOption> fetchCodes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<MenuOption> offers = null;
		try {
			offers = offersService.fetchAllMenuOptions(page, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addOffer(@RequestBody MenuOption menuOption) {
		try {
//			offer.setImage(flogo);
			MenuOption obj = offersService.addMenuOption(menuOption);
			return new ResponseEntity<>(obj, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error while saving product", ex);
			return new ResponseEntity<>("Error while saving Offer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> updateOffer(@RequestBody MenuOption menuOption) {
		try {
			MenuOption obj = offersService.addMenuOption(menuOption);
			return new ResponseEntity<>(obj, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error while saving product", ex);
			return new ResponseEntity<>("Error while saving Offer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteOffers(@RequestBody MenuOption menuOption) {
		System.out.println("Inside delete code ");
		offersService.deleteMenuOption(menuOption);
		return "redirect:/codes";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public List<MenuOption> searchOffers(@RequestBody ProductDto product) {
		return offersService.searchMenuOptions(product, product.getPage(), product.getSize());
	}
	
	@RequestMapping(value = "/parentsByBrand/{brandid}", method = RequestMethod.GET)
	@ResponseBody
	public List<MenuOption> fetchParents(@PathVariable("brandid") Long brandId) {
		return offersService.fetchParents("brand", brandId);
	}


}
