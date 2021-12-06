package com.incon.connect.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incon.connect.ui.dto.RegisterDto;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.services.RegistrationService;
import com.incon.connect.ui.services.UserPointsService;
import com.incon.connect.ui.services.UserService;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserPointsService userPointService;

	@Autowired
	RegistrationService registrationService;

	// @RequestMapping(value="/registration", method = RequestMethod.GET)
	// public String register(@Valid User user){
	// return "register";
	// }

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Response createUser(@RequestBody User user, BindingResult result) {
		Response response = new Response();
		User status = registrationService.saveUser(user);
		if (null != status) {
			response.setSuccess(true);
		} else {
			response.setSuccess(false);
		}
		return response;
	}

	@RequestMapping(value = "/isuserexist", method = RequestMethod.POST)
	@ResponseBody
	public Response isUserNameExists(@RequestBody RegisterDto dto, BindingResult result) {
		Response response = new Response();
		boolean status = registrationService.findByUsername(dto.getUsername());
		if (status) {
			response.setSuccess(status);
		} else {
			response.setSuccess(status);
		}
		return response;
	}

	@RequestMapping(value = "/isemailexist", method = RequestMethod.POST)
	@ResponseBody
	public Response isEmailExists(@RequestBody RegisterDto dto, BindingResult result) {
		Response response = new Response();
		boolean status = registrationService.findByEmailid(dto.getEmailid());
		if (status) {
			response.setSuccess(status);
		} else {
			response.setSuccess(status);
		}
		return response;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Page<User> fetchAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return userService.fetchAllUsers(page, size);
	}

	@RequestMapping(value = "/all-merchants", method = RequestMethod.GET)
	@ResponseBody
	public Page<SubscriberDetails> fetchAllMerchants(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return userPointService.fetchAllMerchants(page, size);
	}

	// @RequestMapping(value = "/save", method = RequestMethod.POST)
	// public @ResponseBody Response addMerchant(@RequestBody @Valid UserPoints
	// userPoints, BindingResult result) {
	// Response response = new Response();
	// UserPoints user;
	// try {
	// user = userPointService.saveUpdateUser(userPoints);
	// List<UserPoints> userList = new ArrayList<>();
	// userList.add(user);
	// response.setSuccess(true);
	// response.setData(userList);
	// } catch (Exception e) {
	// List<FieldError> errors = new ArrayList<>();
	// response.setSuccess(false);
	// FieldError error = new FieldError("merchant", "mobileNumber",
	// e.getMessage());
	// errors.add(error);
	// response.setErrors(errors);
	// response.setMessage(e.getMessage());
	// }
	// return response;
	// }
	//

	@RequestMapping(value = "/brands", method = RequestMethod.GET)
	@ResponseBody
	public Response fetchBrands() {
		Response response = new Response();
		List<User> usersList = userService.fetchDistinctBrands();
		response.setData(usersList);
		return response;
	}

}
