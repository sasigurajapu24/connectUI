package com.incon.connect.ui.services;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.User;

public interface RegistrationService {
	
	public User saveUser(User user);
	public boolean findByUsername(String username);
	public boolean findByEmailid(String emailid);

}
