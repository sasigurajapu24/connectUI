package com.incon.connect.ui.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.incon.connect.ui.entities.User;

public class MyUtils {

	public static User getSessionUser() {
		return getAuth();
	}

	public static User getAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof User) {
				return (User) principal;
			}
		}
		return null;	  
	}
}
