package com.incon.connect.ui.services;

import org.springframework.data.domain.Page;

import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.AppHits;
import com.incon.connect.ui.entities.QRCodes;
import com.incon.connect.ui.entities.TrueCheckCodes;

public interface CodesService {

	Page<QRCodes> fetchAllCodes(int page, int size);

	QRCodes updateCodes(QRCodes code);

	void deleteCodes(QRCodes code);

	Page<QRCodes> searchCodesQR(QRCodeDto code);

	Page<AppHits> getAppHits(Long id, int page, int size);

	Page<QRCodes> fetchUserCodes(int page, int size);

	byte[] generateQrCodes(Integer count, Long prodId);

	QRCodes addCodes(QRCodes uniqCode) throws Exception;

}
