package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.dto.CodeRequestDto;
import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.CodeRequest;
public interface CodeRequestService {
	
	CodeRequest saveMethod(Long id);

	Page<CodeRequest> fetchCodeRequests(CodeRequestDto codeRequestsDto);
	
	List<QRCodeDto> generateQrCodes(Long id, Long reqId) throws Exception;

}
