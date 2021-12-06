package com.incon.connect.ui.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.repositories.ProductRepository;
import com.incon.connect.ui.repositories.UserRepository;
import com.incon.connect.ui.services.UserService;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	ProductRepository productRepository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		logger.info("User[{}] logged in.",username);
		User user = null;
		user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		user.getAuthorities();
		

		boolean enabled = true;
		user.setEnabled(enabled);
		user.setAccountNonExpired(enabled);
		user.setAccountNonLocked(enabled);
		user.setCredentialsNonExpired(enabled);
		
		Collection<? extends GrantedAuthority> authorities = getAuthorities(user.getUserType().getUsertype());
		user.setAuthorities(authorities);
		return user;
	}

	@Override
	public boolean hasRole(String role) {
		// TODO Auto-generated method stub
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (role.equals(auth.getAuthority()))
				return true;
		}
		return false;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(String role) {
		final java.util.List<GrantedAuthority> authList = getGrantedAuthoritie(role);
		return authList;
	}

	public static java.util.List<GrantedAuthority> getGrantedAuthoritie(String roles) {
		final java.util.List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roles));
		return authorities;
	}

	@Override
	public Page<User> fetchAllUsers(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		Page<User> result = userRepository.findAll(pageable); 
		
		result.getContent().forEach(obj -> {
			obj.setCount(productRepository.countByCreatedBy(obj.getId()));
		});
		return result;
	}

	@Override
	public List<User> fetchDistinctBrands() {
		
		return userRepository.findDistinctUsers();
	}
	
}
