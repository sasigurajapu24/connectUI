package com.incon.connect.ui.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.AppHits;
import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.QRCodes;
import com.incon.connect.ui.entities.TrueCheckCodes;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.repositories.AppHitsRepository;
import com.incon.connect.ui.repositories.ProductRepository;
import com.incon.connect.ui.repositories.UniqueCodesRepository;
import com.incon.connect.ui.services.CodesService;
import com.incon.connect.ui.services.DataService;
import com.incon.connect.ui.util.CreateCSVFile;
import com.incon.connect.ui.util.GenerateQRCode;
import com.incon.connect.ui.util.MyUtils;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CodesServiceImpl implements CodesService {

	private static final Logger logger = LoggerFactory.getLogger(CodesServiceImpl.class);

	@Autowired
	UniqueCodesRepository uniqueCodesRepository;

	@Autowired
	ProductRepository productsRepository;

	@Autowired
	AppHitsRepository appHitsRepository;

	@Autowired
	DataService dataService;

	@Autowired
	private GenerateQRCode generateQRCode;

	@Override
	public QRCodes addCodes(QRCodes uniqCode) throws Exception {

		logger.info("Adding codes[{}]", uniqCode.getId());
		try {
//			uniqCode.setCreatedBy(MyUtils.getSessionUser().getId());
			uniqCode = uniqueCodesRepository.save(uniqCode);
//			Product code = productsRepository.findOne(uniqCode.getProduct().getId());
//			uniqCode.setProduct(code);
//			addCodesPerBatch(uniqCode);
		} catch (Exception e) {
			logger.error("Error while adding codes ", e);
			throw new Exception(e);
		}
		return uniqCode;
	}

	/**
	 * @throws Exception 
	 * @throws DataAccessException 
	 * 
	 */
	public void addCodesPerBatch(TrueCheckCodes code) throws Exception {
		CreateCSVFile createCSVFile = new CreateCSVFile();
		File outputFile = createCSVFile.writeCSV(code);
		dataService.loadCodesToDB(outputFile.getName(), code.getProduct().getName());
		outputFile.delete();
	}

	@Override
	public Page<QRCodes> fetchAllCodes(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		return uniqueCodesRepository.findAll(pageable);
	}

	@Override
	public Page<QRCodes> fetchUserCodes(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Page<QRCodes> list = null;
		if (null != user) {
			list = uniqueCodesRepository.findByCreatedBy(user.getId(), pageable);
		} else {
			list = uniqueCodesRepository.findAll(pageable);
		}
		if (null != list)
			logger.info("Products count : " + list.getContent().size());
		return list;
	}

	@Override
	public QRCodes updateCodes(QRCodes code) {
		QRCodes uc = uniqueCodesRepository.getOne(code.getId());
//		System.out.println(" code.getProducctsPerBatch() value is " + code.getNoProductBatch());
		return uniqueCodesRepository.save(code);
	}

	public void deleteCodes(QRCodes code) {
		QRCodes uc = uniqueCodesRepository.getOne(code.getId());
		System.out.println("uc.getCreatedDate() value is  " + uc.getCreatedDate());
		uniqueCodesRepository.delete(uc);
	}

	@Override
	public Page<QRCodes> searchCodesQR(QRCodeDto dto) {
		Product pr = new Product();
		pr.setId(dto.getProduct().longValue());

		Pageable page = new PageRequest(dto.getPage(), dto.getSize(), new Sort(Direction.DESC, "modifiedDate"));
		return null;//uniqueCodesRepository.findByProduct(pr, page);
	}

	@Override
	public Page<AppHits> getAppHits(Long id, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "insertTimestamp"));

		return appHitsRepository.findByProductId(id, pageable);
	}
	
	@Override
	public byte[] generateQrCodes(Integer count, Long prodId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer entryPoint = "manufacturer".equals(user.getUserType().getUsertype()) ? 2 : 1;
		if(Long.valueOf("0").equals(prodId)){
			prodId = null;
		}
		List<String> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			QRCodes code = new QRCodes();
			code.setCode(System.currentTimeMillis()+"");
			code.setProductId(prodId);
			code.setEntryPoint(entryPoint);
			code.setCreatedBy(user.getId().intValue());
			code = uniqueCodesRepository.save(code);
			list.add(1+"-"+code.getId()+"-"+code.getCode());
		}
		byte[] returnBytes = null;
		try {
			returnBytes = generateQRCode.generateCode(list, 1l, "password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnBytes;
	}

}
