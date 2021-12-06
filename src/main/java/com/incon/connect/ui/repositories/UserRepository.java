package com.incon.connect.ui.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incon.connect.ui.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmailid(String emailid);
	
	User findByMobile(String mobile);
	
	@Query("select a from User a GROUP BY a.companyName")
	List<User> findDistinctUsers();

}
