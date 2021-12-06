package com.incon.connect.ui.services.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.entities.UserType;
import com.incon.connect.ui.repositories.ProductRepository;
import com.incon.connect.ui.repositories.UserRepository;
import com.incon.connect.ui.services.RegistrationService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired 
	ProductRepository productRepository;
	
	@Override
	public User saveUser(User user) {
		try {
			
			if(null == user.getId() ){
				User user1 = userRepository.findByMobile(user.getMobile());
				if(null != user1){
					if(user1.getUserType().getId().equals(2)){
						throw new RuntimeException("User already exists for this mobile number");
					}else{
						throw new RuntimeException("Mobile number already used for connect user");
					}
				}
			}
			user.setEmailid(user.getUsername());
			UserType userType = new UserType();
			userType.setId(2l);
			user.setCreatedDate(new Date());
			user.setUserType(userType);
			if (!findByUsername(user.getUsername())) {
				return userRepository.save(user);
			} else {
				return null;
			}
		} catch (Exception ex) {
			logger.error("Error while saving user.", ex);
			return null;
		}
	}

	@Override
	public boolean findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean findByEmailid(String email) {
		User user = userRepository.findByEmailid(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}
