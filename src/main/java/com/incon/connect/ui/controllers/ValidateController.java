package com.incon.connect.ui.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incon.connect.ui.dto.ValidateResponse;
import com.incon.connect.ui.entities.AppHits;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.services.UserPointsService;
import com.incon.connect.ui.services.ValidateService;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/tc")
public class ValidateController {
	
	@Autowired
	ValidateService validateService;
	
	@Autowired
	UserPointsService userpointsService;
	@RequestMapping(value="", method = RequestMethod.GET)
	@ResponseBody
	public List<SubscriberDetails> fetchDepots() {
		List<SubscriberDetails> depots = null;
		try{
			depots = userpointsService.fetchAllUserPoints();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return depots;
	}
	
	@RequestMapping(value="/subservice", method = RequestMethod.GET)
	@ResponseBody
	public List<SubscriberDetails> fetchSubServiceCenter() {
		List<SubscriberDetails> subService = null;
		try{
			subService = userpointsService.fetchSubServiceCenter();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return subService;
	}
	
	@RequestMapping(value="/validate", method = RequestMethod.GET)
	@ResponseBody
	public ValidateResponse findDepot(@PathVariable("code") String code, @PathVariable("msisdn") String  msisdn) {
		return validateService.validateCode(code, msisdn);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ValidateController.class);
	
	@RequestMapping(value = "/otp", method = RequestMethod.GET)
	public @ResponseBody Response sendOtp(HttpServletRequest req) {
		Response resp = new Response();
		String msisdn=req.getParameter("msisdn");
		String number;
		try {
			number = validateService.sendOTP(msisdn);
			resp.setSuccess(true);
			logger.info("opt number-------------->"+number);
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage("Error while processing request.");
		}
        return resp;
	}
	
	@RequestMapping(value = "/validateotp", method = RequestMethod.GET)
	public @ResponseBody Boolean getValidate( Model model,HttpServletRequest req) {
	
		String msisdn=req.getParameter("msisdn");
		String otpnumber=req.getParameter("otp");
		Response resp = new Response();
		Boolean result=validateService.validateOTP(msisdn, otpnumber);
		logger.info("result-------------->"+result);
        return result;
	}
	
	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public @ResponseBody Response saveStoreData(HttpServletRequest req) {
		Response resp = new Response();
		String msisdn=req.getParameter("msisdn");
		String number;
		try {
			number = validateService.sendOTP(msisdn);
			resp.setSuccess(true);
			logger.info("opt number-------------->"+number);
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage("Error while processing request.");
		}
        return resp;
	}
	
	@RequestMapping(value = "/myscans", method = RequestMethod.POST)
	public @ResponseBody Response myscans(HttpServletRequest req) {
		Response resp = new Response();
		String msisdn=req.getParameter("msisdn");
		try {
			Page<AppHits>  hits = validateService.getMyScans(msisdn);
			if(null != hits){
				resp.setData(hits.getContent());
			}
			resp.setSuccess(true);
			logger.info("Total no of hits"+hits.getTotalElements());
		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setMessage("Error while processing request.");
		}
        return resp;
	}
}
