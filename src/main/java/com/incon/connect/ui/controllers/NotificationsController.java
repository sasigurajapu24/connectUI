package com.incon.connect.ui.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incon.connect.ui.entities.Notifications;
import com.incon.connect.ui.entities.TrueCheckCodes;
import com.incon.connect.ui.services.NotificationService;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {

	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String load() {
		return "codes";
	}
	
	
	@RequestMapping(value="/{Id}", method = RequestMethod.GET)
	@ResponseBody
	public Notifications findById(@PathVariable("Id") Long code) {
		return notificationService.findById(code);
	}
	
	
	@RequestMapping(value="/notifications", method = RequestMethod.GET)
	@ResponseBody
	public Page<Notifications> fetchUserNotifications(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="2") int size) {
		Page<Notifications> depots = null;
		try{
			depots = notificationService.fetchUserNotifications(page, size);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return depots;
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	@ResponseBody
	public Page<Notifications> fetchAllNotifications(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="2") int size) {
		Page<Notifications> depots = null;
		try{
			depots = notificationService.fetchAllNotifications(page, size);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return depots;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public @ResponseBody Response addCodes(@RequestBody @Valid Notifications code, BindingResult result){
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		if (result.hasErrors()) {
			response.setSuccess(false);
			response.setErrors(result.getFieldErrors());
			response.setMessage("Unable to add notifications");
			return response;
		}
		notificationService.addNotification(code);
		return response;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public @ResponseBody Response updateCodes(@RequestBody @Valid Notifications code, BindingResult result){
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		if (result.hasErrors()) {
			response.setSuccess(false);
			response.setErrors(result.getFieldErrors());
			response.setMessage("Unable to add codes");
			return response;
		}
		notificationService.updateNotification(code);
		return response;
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteCodes(Notifications code){
		System.out.println("Inside delete code ");
//		notificationService.(code);
		return "redirect:/add-notification";
	}
	
	
	@RequestMapping(value = "/qrsearch", method = RequestMethod.POST)
	@ResponseBody
	public Page<Notifications> searchProducts(@RequestBody String query) {
		return notificationService.searchNotifications(query, 0, 10);
	}
	
	
}
