/**
 * 
 */
package com.incon.connect.ui.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incon.connect.ui.dto.QRCodeDto;

/**
 * @author bogavalli.srinivas
 *
 */
public class UniqueCodeRowMapper implements RowMapper<QRCodeDto> {

	@Override
	public QRCodeDto mapRow(ResultSet rs, int arg1) throws SQLException {
		
		QRCodeDto dto = new QRCodeDto();
		
		dto.setCodeId(rs.getInt("code_id"));
		dto.setCode(rs.getString("code"));
		dto.setBatchNo(rs.getString("batchno"));
		dto.setSerialNo(rs.getString("serial"));
		dto.setProduct(rs.getInt("product"));
		dto.setuCodeId(rs.getInt("ucodeid"));
		return dto;
	}
	
	

}
