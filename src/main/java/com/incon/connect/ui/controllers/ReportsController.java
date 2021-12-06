package com.incon.connect.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.incon.connect.ui.services.ReportsService;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	ReportsService reportsService;
	
}		
