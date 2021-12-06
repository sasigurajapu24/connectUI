package com.incon.connect.ui.services.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.incon.connect.ui.dto.ValidateResponse;
import com.incon.connect.ui.entities.AppHits;
import com.incon.connect.ui.entities.FakeHistory;
import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.TrueCheckCodes;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.repositories.AppHitsRepository;
import com.incon.connect.ui.repositories.FakeHistoryRepository;
import com.incon.connect.ui.repositories.HitCountRepository;
import com.incon.connect.ui.repositories.ProductRepository;
import com.incon.connect.ui.repositories.UniqueCodesRepository;
import com.incon.connect.ui.repositories.SubscriberDetailsRepository;
import com.incon.connect.ui.services.ValidateService;
import com.incon.connect.ui.util.URLHitUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ValidateServiceImpl implements ValidateService {

	private static final Logger logger = LoggerFactory.getLogger(ValidateServiceImpl.class);

	@Autowired
	SubscriberDetailsRepository userPointRepository;

	@Autowired
	UniqueCodesRepository uniqueCodesRepository;

	@Autowired
	HitCountRepository hitCountRepository;

	@Autowired
	ProductRepository productCodesRepository;
	
	
	@Autowired
	FakeHistoryRepository fakeHistoryRepository;
	
	@Autowired
	AppHitsRepository appHitsRepository;

	@Override
	public ValidateResponse validateCode(String code, String msisdn) {
		// TODO Auto-generated method stub

		String chars = "";
		String digits = "";
		String random = "";

		if (StringUtils.isNotBlank(code)) {
			chars = code.substring(0, 3);
			digits = code.substring(4, 6);
			random = code.substring(7, code.length());
		}
		
		ValidateResponse resp = new ValidateResponse();

//		//UniqueCodes uniqCode = uniqueCodesRepository.findByCharsAndDigits(chars, digits);
//
//		UserPoints uPoints = userPointRepository.findByMsisdn(msisdn);
//		
//		if (null != uniqCode) {
//			Products pr = uniqCode.getProduct();
//			AppHits app = new AppHits();
//			app.setCode(chars+digits+random);
//			app.setIsValid("Y");
//			app.setMsisdn(msisdn);
//			app.setProduct(pr);
//			int hitsCount = appHitsRepository.countByMsisdnAndProductId(msisdn, pr.getId());
//			
//			if((hitsCount+1) % pr.getHits() == 0){
//				uPoints.setPoints((uPoints.getPoints() != null ? uPoints.getPoints() : 0)+ pr.getPoint());
//			}
//			
//			resp.setValid(true);
//			resp.setOfferMessage(pr.getPromotionMsg());
//			resp.setThanksMessage(pr.getThanksMsg());
//			resp.setTotalPointsMessage(pr.getName() + " points earned." );
//			resp.setPointsReqMessage("  more points for surprise." );
//			resp.setPointsEarned(uPoints.getPoints());
//			
//			if(uPoints.getPoints() == pr.getTargetPoints()){
//				resp.setTargetReachedMessage(pr.getPointtgtMsg());
//			}
//		}else{
//			resp.setValid(false);
//		}
		
		return resp;
	}

	@Override
	public String sendOTP(String msisdn) throws Exception {
		String randomNumber = "";
		randomNumber = RandomStringUtils.randomNumeric(6);
		try {
			SubscriberDetails user = userPointRepository.findByMsisdn(msisdn);
			if(null == user){
				user = new SubscriberDetails();
			}
			user.setMsisdn(msisdn);
			user.setOtp(randomNumber);
			userPointRepository.save(user);
			String apiURL = "http://bhashsms.com/api/sendmsg.php?user=bogavalli&pass=98877665&sender=ValueB&phone=$$MSISDN$$&text=$$MESSAGE$$&priority=sdnd&stype=normal";
			apiURL = apiURL.replace("$$MESSAGE$$", URLEncoder.encode("Your otp for registering to true check app is : "+randomNumber, "UTF-8"));
			apiURL = apiURL.replace("$$MSISDN$$", msisdn);
			logger.info("API URL  :: "+apiURL);
			String resp = URLHitUtil.hittheData(apiURL);
			logger.info("Response after hitting URL : "+resp);
			System.out.println("Response after hitting URL : "+resp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return randomNumber;
	}

	@Override
	public Boolean validateOTP(String msisdn, String otp) {
		Boolean result = false;
		try {
			SubscriberDetails user = userPointRepository.findByMsisdnAndOtp(msisdn, otp);
			if (null != user) {

				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String insertedDate = df.format(user.getInsertTimestamp());
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				String todaysDate = df.format(cal.getTime());
				if (todaysDate.equals(insertedDate)) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public FakeHistory saveStore(FakeHistory storeData) {
		
		FakeHistory fh = fakeHistoryRepository.save(storeData);
		
		return fh;
	}

	@Override
	public Page<AppHits> getMyScans(String msisdn) {
		Pageable pageable = new PageRequest(1, 10, new Sort(Direction.DESC, "insertTimestamp"));
		Page<AppHits> list = appHitsRepository.findAll(pageable);
		return list;
	}

}
