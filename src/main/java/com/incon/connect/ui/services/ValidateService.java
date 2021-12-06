package com.incon.connect.ui.services;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.dto.ValidateResponse;
import com.incon.connect.ui.entities.AppHits;
import com.incon.connect.ui.entities.FakeHistory;

public interface ValidateService {

	ValidateResponse validateCode(String code, String msisdn);

	public String sendOTP(String msisdn) throws Exception;

	// public String getOTP();
	public Boolean validateOTP(String msisdn, String otp);
	
	
	FakeHistory saveStore(FakeHistory storeData );

	Page<AppHits> getMyScans(String msisdn);

}
