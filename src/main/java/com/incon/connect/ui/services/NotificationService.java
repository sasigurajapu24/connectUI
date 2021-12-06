package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.entities.Notifications;


public interface NotificationService {
	
	Notifications addNotification(Notifications uniqCode);

	Page<Notifications> fetchAllNotifications(int page, int size);

	Notifications updateNotification(Notifications code);

	Page<Notifications> searchNotifications(String query, int page, int size);

	Page<Notifications> fetchUserNotifications(int page, int size);

	Notifications findById(Long code);


}
