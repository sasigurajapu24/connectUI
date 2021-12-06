package com.incon.connect.ui.services;

import com.incon.connect.ui.dto.EmailTemplateDto;


public interface EmailService {
	void sendTemplate(final EmailTemplateDto templateDTOMap);
	
	void sendPassword(final EmailTemplateDto templateDTOMap);
}
