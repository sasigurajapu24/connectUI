package com.incon.connect.ui.services.impl;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.dto.CodeRequestDto;
import com.incon.connect.ui.dto.EmailTemplateDto;
import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.CodeRequest;
import com.incon.connect.ui.entities.QRCodes;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.entities.SubscriberDetails;
import com.incon.connect.ui.repositories.CodeRequestRepository;
import com.incon.connect.ui.repositories.UniqueCodesRepository;
import com.incon.connect.ui.services.CodeRequestService;
import com.incon.connect.ui.services.DataService;
import com.incon.connect.ui.services.EmailService;
import com.incon.connect.ui.util.GenerateQRCode;
import com.incon.connect.ui.util.MyUtils;
import com.incon.connect.ui.util.URLHitUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CodeRequestServiceImpl implements CodeRequestService {

	private static final Logger logger = LoggerFactory.getLogger(UserPointServiceImpl.class);

	@Autowired
	CodeRequestRepository codeRequestRepository;

	@Autowired
	UniqueCodesRepository uniqueCodesRepository;

	@Autowired
	GenerateQRCode generateQRCode;

	@Autowired
	EmailService emailService;

	@Autowired
	DataService myBean;

	@Override
	public CodeRequest saveMethod(Long id) {
		CodeRequest codeRequest = new CodeRequest();
		CodeRequest request = codeRequestRepository.findByCodeId(id);
		if (request == null) {
			QRCodes codes = uniqueCodesRepository.findOne(id);
//			codeRequest.setCode(code);
			codeRequest.setStatus(0);
			codeRequest.setUser(MyUtils.getSessionUser());
			codeRequest = codeRequestRepository.save(codeRequest);

			codes.setStatus(1);
			uniqueCodesRepository.save(codes);
		} else {
			codeRequest = new CodeRequest();
		}
		return codeRequest;
	}

	@Override
	public Page<CodeRequest> fetchCodeRequests(CodeRequestDto codeRequestsDto) {
		Pageable pageable = new PageRequest(codeRequestsDto.getPage(), codeRequestsDto.getSize(), new Sort(Direction.DESC, "createdDate"));
		Page<CodeRequest> result = codeRequestRepository.findAll(pageable);
		// List<CodeRequestDto> dtoList = new ArrayList<CodeRequestDto>();
		// if(null != result && null != result.getContent()){
		// for (CodeRequest codeRequest : result) {
		// CodeRequestDto dto = new CodeRequestDto();
		// dto.setBatchCode(codeRequest.getCode().getBatchCode());
		// dto.setUserName(codeRequest.getUser().getUsername());
		// dto.setCompanyName(codeRequest.getUser().getCompanyName());
		// dto.setProductName(codeRequest.getCode().getProduct().getName());
		// dto.setReqStatus(codeRequest.getStatus());
		// dto.setCodeId(codeRequest.getCode().getId());
		// dtoList.add(dto);
		// }
		// }
		return result;
	}

	/**
	 * 
	 */
	@Override
	public List<QRCodeDto> generateQrCodes(Long id, Long reqId) throws Exception {
		QRCodes code = uniqueCodesRepository.findOne(id);
		CodeRequest request = codeRequestRepository.findOne(reqId);
		List<QRCodeDto> list = myBean.getCodes(code);
		List<String> list1 = new ArrayList<String>(); //myBean.getCodes(code);
		String password = RandomStringUtils.randomAlphanumeric(8);
		byte[] file = generateQRCode.generateCode(list1, code.getId(), password);
		EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
//		emailTemplateDto.setFile(file);
		emailTemplateDto.setBatchNo(code.getBatchCode());
//		emailTemplateDto.setProductName(code.getProduct().getName());
		emailTemplateDto.setUser(request.getUser());
		emailService.sendTemplate(emailTemplateDto);
		request.setStatus(1);
		codeRequestRepository.save(request);
		code.setStatus(1);
		uniqueCodesRepository.save(code);

		sendPassword(password, request.getUser());
		return list;
	}

	private void sendPassword(String password, User user) throws Exception {
		try {
			String apiURL = "http://bhashsms.com/api/sendmsg.php?user=bogavalli&pass=98877665&sender=ValueB&phone=$$MSISDN$$&text=$$MESSAGE$$&priority=sdnd&stype=normal";
			apiURL = apiURL.replace("$$MESSAGE$$", URLEncoder.encode("Your password to open codes document : " + password, "UTF-8"));
			apiURL = apiURL.replace("$$MSISDN$$", user.getMobile());
			logger.info("API URL  :: " + apiURL);
			String resp = URLHitUtil.hittheData(apiURL);
			logger.info("Response after hitting URL : " + resp);
			System.out.println("Response after hitting URL : " + resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != user) {
			EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
			emailTemplateDto.setTo(user.getUsername());
			emailTemplateDto.setSubject("Truecheck QRCODES credentials");
			emailTemplateDto.setMessage("<!DOCTYPE html>" + "<html xmlns:th=\"http://www.thymeleaf.org\">" + "<head></head><body><h3>Dear User</h3><br/><h2>Below are the credentials for Opening PDF:  </h2>" + "	<br/> <br/> <br/>" + "Password : "
					+ password + " <br/> <br/> " + "Thanks, <br/> " + "Truecheck - Anti counterfiet <br/></body></html>");
			emailService.sendPassword(emailTemplateDto);
		}

	}

}
