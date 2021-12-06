package com.incon.connect.ui.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incon.connect.ui.entities.Brand;
import com.incon.connect.ui.entities.ProductCategory;
import com.incon.connect.ui.entities.ProductDivision;
import com.incon.connect.ui.services.ConfigurationService;
import com.incon.connect.ui.services.impl.CodesServiceImpl;

@Controller
@RequestMapping("/configs")
public class ConfigurationsController {

	private static final Logger logger = LoggerFactory.getLogger(CodesServiceImpl.class);

	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String load() {
		return "offers";
	}

	@RequestMapping(value = "/add-category", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addCategory(@RequestBody ProductCategory productCategory) {
		ProductCategory category = null;
		category = configurationService.addCategory(productCategory);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-division", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addDivision(@RequestBody ProductDivision productDivision) {
		ProductDivision category = null;
		category = configurationService.addDivision(productDivision);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-brand", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addBrand(@RequestBody Brand brand) {
		Brand category = null;
		category = configurationService.addBrand(brand);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/getcategories", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getCategory(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<ProductCategory> categories = configurationService.getCategories(page, size);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/getdivisions", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getDivision(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<ProductDivision> category = configurationService.getDivisions(page, size);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/getbrands", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> addCategory(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Brand> category = null;
		category = configurationService.getBrands(page, size);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

}
