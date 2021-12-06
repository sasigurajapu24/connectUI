package com.incon.connect.ui.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incon.connect.ui.dto.QRCodeDto;
import com.incon.connect.ui.entities.QRCodes;
import com.incon.connect.ui.services.DataService;
import com.incon.connect.ui.util.UniqueCodeRowMapper;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataServiceImpl implements DataService{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public DataServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 
	 * @param productName
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Boolean createProductTable(String productName) throws DataAccessException{
		productName = productName.replaceAll(" ","");
		String query = "CREATE TABLE codes.`" + productName.toLowerCase() + "_codes` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `code_id` int(11) NOT NULL,"
				+ "  `seq_no` int(11) Not NULL, "
				+ "  `code` varchar(11) Not NULL,"
				+ "	 `status` int(11) DEFAULT 0 , "
				+ "   PRIMARY KEY (`id`))";
		jdbcTemplate.execute(query);
		return true;
	}
	
	/**
	 * For fetching codes
	 * @param code
	 * @return
	 */
	@Override
	public List<QRCodeDto> getCodes(QRCodes code) {
		String productName = null;//code.getProduct().getName();
		productName = productName.replaceAll(" ","");
		String query = "select c.code_id, c.code,uc.batch_code as batchno,c.seq_no as serial, uc.product_id as product, c.id as ucodeid from codes.`" + productName.toLowerCase() + "_codes` c inner join truecheck.true_check_codes uc on (c.code_id = uc.id) where code_id = "+code.getId();
		return jdbcTemplate.query(query, new Object[]{}, new UniqueCodeRowMapper());
	}
	
	/**
	 * 
	 * @param outputFile
	 * @param productName
	 * @throws Exception 
	 */
	@Override
	public void loadCodesToDB(String outputFile, String productName) throws Exception {
		try{
			productName = productName.replaceAll(" ","");
			String loadQuery = "LOAD DATA LOCAL INFILE '" + outputFile + "' INTO TABLE codes.`" + productName.toLowerCase() + "_codes` FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' ( code_id, seq_no, code) ";
			jdbcTemplate.execute(loadQuery);
		}catch(DataAccessException ex){
			throw new Exception(ex.getMessage());
		}
//		String query = "delete from `codes." + productName.toLowerCase() + "_codes`  where code=0";
//		 jdbcTemplate.execute(query);
	}

}
