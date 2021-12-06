package com.incon.connect.ui.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.services.ReportsService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportsServiceImpl implements ReportsService {

	private static final Logger logger = LoggerFactory.getLogger(ReportsServiceImpl.class);

}
