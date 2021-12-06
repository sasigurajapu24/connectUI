package com.incon.connect.ui.services.impl;

import java.util.List;

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

import com.incon.connect.ui.dto.EmailTemplateDto;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.repositories.SubscriberDetailsRepository;
import com.incon.connect.ui.services.EmailService;
import com.incon.connect.ui.services.UserPointsService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserPointServiceImpl implements UserPointsService {

	private static final Logger logger = LoggerFactory.getLogger(UserPointServiceImpl.class);

	@Autowired
	private SubscriberDetailsRepository userPointsRepository;
	
	@Autowired
	EmailService emailService;

	@Override
	public SubscriberDetails findUserPoints(Long code) {
		logger.info("Find UserPoint[{}]", code);
		return userPointsRepository.findOne(code);
	}

	@Override
	public List<SubscriberDetails> fetchSubServiceCenter() {
		logger.info("Find UserPoint[{}]");
		return userPointsRepository.findByUsertypeAndApproveStatus(7, -1);
	}

	@Override
	public List<SubscriberDetails> fetchAllUserPoints() {
		logger.info("Find UserPoint[{}]");
		return userPointsRepository.findAll();
	}
	@Override
	public SubscriberDetails addUserPoints(SubscriberDetails userPoints) {
		logger.info("Adding UserPoint[{}]", userPoints.getId());
		return userPointsRepository.save(userPoints);
	}

	@Override
	public SubscriberDetails updateUserPoints(SubscriberDetails userPoint) {
		logger.info("Update UserPoint[{}]", userPoint.getId());
		SubscriberDetails update = findUserPoints(userPoint.getId());
		return userPointsRepository.save(update);

	}

	@Override
	public SubscriberDetails saveUpdateUser(SubscriberDetails userPoints) {
		logger.info("SaveUpdateUser UserPoint[{}]", userPoints);
		if(null == userPoints.getId() ){
			SubscriberDetails user = userPointsRepository.findByMsisdn(userPoints.getMsisdn());
			if(null != user){
				if(user.getUsertype() == 4){
					throw new RuntimeException("Merchant already exists for this mobile number");
				}else{
					throw new RuntimeException("Mobile number already used for true check user");
				}
			}
		}
		userPoints.setUsertype(4);
		SubscriberDetails user = userPointsRepository.saveAndFlush(userPoints);
		if(null != user && StringUtils.isNotBlank(user.getPasswordStr())){
			EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
			emailTemplateDto.setTo(user.getEmail());
			emailTemplateDto.setSubject("Truecheck merchant login credentials");
			emailTemplateDto.setMessage("<!DOCTYPE html>"+
"<html xmlns:th=\"http://www.thymeleaf.org\">"+
"<head></head><body><h3>Dear "+user.getName()+"</h3><br/><h2>Below are the login credentials for true check app login :  </h2>"
		+ "	<br/> <br/> <br/>"
		+ "UserName : "+user.getMsisdn() +" OR "+user.getEmail()+" <br/>"
		+ "Password : "+user.getPasswordStr() +" <br/> <br/> <br/> <br/> "
		+ "Thanks, "
		+ "Truecheck - Anti counterfiet <br/></body></html>");
			emailService.sendPassword(emailTemplateDto);
		}
		return user;
	}

	@Override
	public Page<SubscriberDetails> fetchAllMerchants(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		Page<SubscriberDetails> result = userPointsRepository.findByUsertype(4, pageable); 
		return result;
	}
}
