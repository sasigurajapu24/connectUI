package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.entities.SubscriberDetails;

public interface UserPointsService {
	SubscriberDetails findUserPoints(Long code);

	List<SubscriberDetails> fetchAllUserPoints();
	
	public List<SubscriberDetails> fetchSubServiceCenter();

	SubscriberDetails addUserPoints(SubscriberDetails UserPoints);

	SubscriberDetails updateUserPoints(SubscriberDetails UserPoints);
	
	public SubscriberDetails saveUpdateUser(SubscriberDetails userPoints);

	Page<SubscriberDetails> fetchAllMerchants(int page, int size);

}