/**
 * 
 */
package com.incon.connect.ui.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.QRCodes;

/**
 * @author srinivas.bogavalli
 *
 */
public interface DataService {

	Boolean createProductTable(String productName) throws DataAccessException;

	List<QRCodeDto> getCodes(QRCodes code)  throws DataAccessException;

	void loadCodesToDB(String outputFile, String productName)  throws DataAccessException, Exception;

}
