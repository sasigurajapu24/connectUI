package com.incon.connect.ui.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incon.connect.ui.dto.CodeRequestDto;
import com.incon.connect.ui.dto.EmailTemplateDto;
import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.AppHits;
import com.incon.connect.ui.entities.CodeRequest;
import com.incon.connect.ui.entities.QRCodes;
import com.incon.connect.ui.services.CodeRequestService;
import com.incon.connect.ui.services.CodesService;
import com.incon.connect.ui.services.EmailService;
import com.incon.connect.ui.services.impl.CodesServiceImpl;
import com.incon.connect.ui.util.Response;

@Controller
@RequestMapping("/codes")
public class CodesController {

	private static final Logger logger = LoggerFactory.getLogger(CodesServiceImpl.class);
	@Autowired
	CodesService codesService;
	@Autowired
	CodeRequestService codeRequestService;
	@Autowired
	EmailService emailService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String load(@RequestParam("prod_id") Long productId, Model model) {
		model.addAttribute("productId", productId);
		return "add-codes";
	}

	@RequestMapping(value = "/codes", method = RequestMethod.GET)
	@ResponseBody
	public Page<QRCodes> fetchUserCodes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<QRCodes> depots = null;
		try {
			depots = codesService.fetchUserCodes(page, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depots;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Page<QRCodes> fetchCodes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<QRCodes> depots = null;
		try {
			depots = codesService.fetchAllCodes(page, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depots;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Response addCodes(@RequestBody @Valid QRCodes code, BindingResult result) {
		Response response = new Response();
		if (result.hasErrors()) {
			response.setSuccess(false);
			response.setErrors(result.getFieldErrors());
			response.setMessage("Unable to add codes");
			return response;
		}
		try {
			response.setSuccess(true);
			response.setMessage("");
			codesService.addCodes(code);
		} catch (Exception e) {
			logger.error("Error While adding codes ", e);
			response.setSuccess(false);
			response.setErrors(result.getFieldErrors());
			response.setMessage("Unable to add codes");
		}

		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Response updateCodes(@RequestBody @Valid QRCodes code, BindingResult result) {
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage("");
		if (result.hasErrors()) {
			response.setSuccess(false);
			response.setErrors(result.getFieldErrors());
			response.setMessage("Unable to add codes");
			return response;
		}
		codesService.updateCodes(code);
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteCodes(QRCodes code) {
		System.out.println("Inside delete code ");
		codesService.deleteCodes(code);
		return "redirect:/codes";
	}

	@RequestMapping(value = "/qrsearch", method = RequestMethod.POST)
	@ResponseBody
	public Page<QRCodes> searchProducts(@RequestBody QRCodeDto dto) {
		return codesService.searchCodesQR(dto);
	}

	@RequestMapping(value = "/generate/{codeid}/{reqid}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean generateQRCodes(@PathVariable("codeid") Long id, @PathVariable("reqid") Long reqid, HttpServletRequest request) {
		try {
			codeRequestService.generateQrCodes(id, reqid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/hits/{productid}", method = RequestMethod.GET)
	@ResponseBody
	public Page<AppHits> getHits(@PathVariable("productid") Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return codesService.getAppHits(id, page, size);
	}

	@RequestMapping(value = "/requestCode/{uniqueCodeId}", method = RequestMethod.POST)
	public @ResponseBody Response requestCodes(@PathVariable("uniqueCodeId") Long code) {
		Response response = new Response();

		CodeRequest codeRequest = codeRequestService.saveMethod(code);
		if (codeRequest != null) {
			response.setSuccess(true);
			response.setMessage("");
		} else {
			response.setSuccess(false);
			response.setMessage("Request Code Already Exist");
		}
		return response;
	}

	@RequestMapping(value = "/requests", method = RequestMethod.GET)
	@ResponseBody
	public Page<CodeRequest> fetchCodeRequests(CodeRequestDto codeRequestsDto) {
		Page<CodeRequest> result = codeRequestService.fetchCodeRequests(codeRequestsDto);
		return result;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public boolean test() {
		EmailTemplateDto obj = new EmailTemplateDto();
		emailService.sendTemplate(obj);
		return true;
	}
	
	
	@RequestMapping(value = "/generateqr/count/{count}",method = RequestMethod.GET)
	public void generateQrCodes(@PathVariable(value= "count") Integer count, @RequestParam(required=false, defaultValue= "0", value= "prodid") Long prodId,  HttpServletResponse response){
			
			byte[] contents  = codesService.generateQrCodes(count, prodId);
			
			response.setContentType("application/pdf"); // in my example this
			response.setContentLength(contents.length);
			response.setHeader("Content-Disposition", "attachment; filename= QRCodes.pdf");
			try {
				FileCopyUtils.copy(new ByteArrayInputStream(contents), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
