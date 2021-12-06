package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.dto.ProductDto;
import com.incon.connect.ui.entities.Offers;

public interface OffersService {

	Page<Offers> fetchAllOffers(int page, int size);

	void addOffer(Offers offer);

	void updateOffer(Offers offer);

	void deleteOffer(Offers code);

	List<Offers> searchOffers(ProductDto product, int page, int size);
	
	

}
