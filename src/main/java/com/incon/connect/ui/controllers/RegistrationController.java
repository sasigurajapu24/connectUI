package com.incon.connect.ui.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incon.connect.ui.dto.RegisterDto;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.services.RegistrationService;
import com.incon.connect.ui.services.impl.ProductServiceImpl;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	RegistrationService registraiontService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String load() {
		return "codes";
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String register(@Valid User user){
		return "register";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public Response createUser(@RequestParam(value = "user") String userInfo,
	        @RequestParam(value = "file") MultipartFile file,HttpServletRequest request) throws IOException {
		User user = null;
		System.out.println("Inside File upload" + userInfo);
		ObjectMapper mapper = new ObjectMapper();
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		try {
			user = mapper.readValue(userInfo, User.class);
			user.setImage(file);
			byte[] flogo=null;
			if(!user.getImage().isEmpty()){
				flogo=user.getImage().getBytes();
//				 BufferedImage io = ImageIO.read(user.getImage().getInputStream());
//				 io.getHeight();
//				 io.getWidth();
//				 if(56 < io.getHeight() || 56 < io.getWidth()){
//					 logger.error("Error while saving user, log size not in range");
//					 response.setSuccess(false);
//					 response.setMessage("Max size allowed for logo is 56 x 56");
//					 return response;
//				 }
			}
			user.setLogo(flogo);
			User userObj = registraiontService.saveUser(user);
//			List<User> users = new ArrayList<>();
//			users.add(userObj);
//			response.setData(users);
			
		}catch(Exception ex){
			logger.error("Error while saving user", ex);
			response.setSuccess(false);
			response.setMessage("Error while saving user");
		}
		return response ;
//		boolean status=registraiontService.saveUser(user);
//			if(status){
//		  response.setSuccess(status);
//			}else{
//				response.setSuccess(status);
//		}
//		return response;
	}
	@RequestMapping(value="/isuserexist", method = RequestMethod.POST)
	@ResponseBody
	public Response isUserNameExists(@RequestBody RegisterDto dto, BindingResult result){
		Response response = new Response(); 
		boolean status=registraiontService.findByUsername(dto.getUsername());
		if(status){
			response.setSuccess(status);
		}else{
			response.setSuccess(status);
		}
		return response;
	}
	
	@RequestMapping(value="/isemailexist", method = RequestMethod.POST)
	@ResponseBody
	public Response isEmailExists( @RequestBody RegisterDto dto, BindingResult result){
		Response response = new Response();
		boolean status=registraiontService.findByEmailid(dto.getEmailid());
		if(status){
			response.setSuccess(status);
		}else{
			response.setSuccess(status);
		}
		return response;
	}
	
}
