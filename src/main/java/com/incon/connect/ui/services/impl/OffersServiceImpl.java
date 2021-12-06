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
import com.incon.connect.ui.entities.Offers;
import com.incon.connect.ui.repositories.OffersRepository;
import com.incon.connect.ui.services.OffersService;
import com.incon.connect.ui.util.MyUtils;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OffersServiceImpl implements OffersService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private OffersRepository offersRepository;

	@Override
	public Page<Offers> fetchAllOffers(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		Page<Offers> result = offersRepository.findAll(pageable); 
		return result;
	}

	@Override
	public void addOffer(Offers offer) {
		logger.info("Adding products[{}]", offer.getId());
		offer.setCreatedBy(MyUtils.getSessionUser().getId());
		offersRepository.save(offer);
	}

	@Override
	public void updateOffer(Offers offer) {
		logger.info("Update offers[{}]",  offer.getId());
		try {
//			if (null != offer.getImage()) {
//				//products.setLogo();
//			} else {
//				Offers update = offersRepository.findOne(offer.getId());
//				offer.setImage(update.getImage());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		offersRepository.save(offer);
	}

	@Override
	public void deleteOffer(Offers code) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Offers> searchOffers(ProductDto product, int page, int size) {
		logger.info("Searching product.search");
//			Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		List<Offers> results = null ;//offersRepository.findByNameLikeIgnoreCase(product.getQuery());
		return results;
	}

}
