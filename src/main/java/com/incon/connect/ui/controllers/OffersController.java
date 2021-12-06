package com.incon.connect.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.Offers;
import com.incon.connect.ui.services.OffersService;
import com.incon.connect.ui.services.impl.CodesServiceImpl;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/offers")
public class OffersController {

	private static final Logger logger = LoggerFactory.getLogger(CodesServiceImpl.class);

	
	@Autowired
	private OffersService offersService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String load() {
		return "offers";
	}

	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	@ResponseBody
	public Page<Offers> fetchCodes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Offers> offers = null;
		try {
			offers = offersService.fetchAllOffers(page, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offers;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Response addOffer(@RequestParam(value = "offer") String offerInfo,@RequestPart(value = "image", required = false) MultipartFile file) {
		Offers offer = null;
		System.out.println("Inside File upload" + offerInfo);
		ObjectMapper mapper = new ObjectMapper();
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		List<FieldError> errors = new ArrayList<>();
		try {
			offer = mapper.readValue(offerInfo, Offers.class);
			byte[] flogo = null;
			if (null != file && !file.isEmpty()) {
				flogo = file.getBytes();
			}

//			offer.setImage(flogo);
			offersService.addOffer(offer);
		} catch (Exception ex) {
			logger.error("Error while saving product", ex);
			FieldError error = new FieldError("offer", "logo", ex.getMessage());
			errors.add(error);
			response.setErrors(errors);
			response.setSuccess(false);
			response.setMessage("Error while saving Offer");
		}

		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Response updateOffer(@RequestParam(value = "offer") String offerInfo,@RequestPart(value = "image", required = false) MultipartFile file) {
		Offers offer = null;
		System.out.println("Inside File upload" + offerInfo);
		ObjectMapper mapper = new ObjectMapper();
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		List<FieldError> errors = new ArrayList<>();
		try {
			offer = mapper.readValue(offerInfo, Offers.class);
			byte[] flogo = null;
			if (null != file && !file.isEmpty()) {
				flogo = file.getBytes();
			}

//			offer.setImage(flogo);
			offersService.updateOffer(offer);
		} catch (Exception ex) {
			logger.error("Error while saving product", ex);
			FieldError error = new FieldError("offer", "logo", ex.getMessage());
			errors.add(error);
			response.setErrors(errors);
			response.setSuccess(false);
			response.setMessage("Error while saving Offer");
		}

		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteOffers(Offers code) {
		System.out.println("Inside delete code ");
		offersService.deleteOffer(code);
		return "redirect:/codes";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Offers> searchOffers(@RequestBody ProductDto product) {
		return offersService.searchOffers(product, product.getPage(), product.getSize());
	}


}
