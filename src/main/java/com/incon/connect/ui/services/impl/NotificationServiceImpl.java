package com.incon.connect.ui.services.impl;

import java.util.Date;
import java.util.List;

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

import com.incon.connect.ui.entities.Notifications;
import com.incon.connect.ui.entities.Product;
import com.incon.connect.ui.entities.User;
import com.incon.connect.ui.repositories.NotificationsRepository;
import com.incon.connect.ui.repositories.ProductRepository;
import com.incon.connect.ui.services.NotificationService;
import com.incon.connect.ui.util.MyUtils;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	NotificationsRepository notificationsRepository;
	@Autowired
	ProductRepository productsRepository;
	
	@Override
	public Notifications addNotification(Notifications uniqCode) {
		// TODO Auto-generated method stub
		logger.info("Adding depot[{}]", uniqCode.getId());
		String currentDate=new java.sql.Timestamp(new java.util.Date().getTime()).toString();
		uniqCode.setCreatedBy(MyUtils.getSessionUser());
		uniqCode.setCompanyId(uniqCode.getProductId());
		uniqCode = notificationsRepository.save(uniqCode);
//		Product code = productsRepository.findOne(uniqCode.getProduct().getId());
//		uniqCode.setProduct(code);
		return uniqCode;
	}
	

	@Override
	public Page<Notifications> fetchAllNotifications(int page, int size) {
		Pageable pageable =   new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		return notificationsRepository.findAll(pageable);
	}
	

	@Override
	public Page<Notifications> fetchUserNotifications(int page, int size) {
		Pageable pageable =   new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		List<Product> products = null;
		User user = MyUtils.getSessionUser();
		Page<Notifications> notifications = null ;//notificationsRepository.findbyCreatedBy(user.getId(), pageable) ;
		return notifications;
	}

	@Override
	public Notifications updateNotification(Notifications code) {
		Notifications uc=notificationsRepository.getOne(code.getId());
		uc.setNotification(code.getNotification());
		uc.setFromDate(code.getFromDate());
		uc.setToDate(code.getToDate());
		System.out.println("uc.getCreatedDate() value is  "+uc.getCreatedDate());
		String currentDate=new java.sql.Timestamp(new java.util.Date().getTime()).toString();
		uc.setModifiedBy(MyUtils.getSessionUser().getId());
		uc.setModifiedDate(new Date());
//		System.out.println(" code.getProducctsPerBatch() value is "+code.getProductsPerBatch());
		return notificationsRepository.save(uc);
	}
	
//	public void deleteCodes(UniqueCodes code)
//	{
//		UniqueCodes uc=uniqueCodesRepository.getOne(code.getId());
//		System.out.println("uc.getCreatedDate() value is  "+uc.getCreatedDate());
//		uniqueCodesRepository.delete(uc);
//	}

	@Override
	public Page<Notifications> searchNotifications(String query, int page, int size) {
		Pageable pageable =   new PageRequest(page, size, new Sort(Direction.DESC, "createdDate"));
		return null ; //notificationsRepository.findbyProductLike( query, pageable);
	}


	@Override
	public Notifications findById(Long code) {
		// TODO Auto-generated method stub
		return notificationsRepository.findOne(code);
	}


}
