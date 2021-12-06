package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.entities.User;

public interface UserService {

	public boolean hasRole(String role);

	public Page<User> fetchAllUsers(int page, int size);

	public List<User> fetchDistinctBrands();

}
