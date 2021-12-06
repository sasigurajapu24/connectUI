package com.incon.connect.ui.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang3.RandomStringUtils;

import com.incon.connect.ui.entities.TrueCheckCodes;

public class CreateCSVFile implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 200672569489168956L;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public File writeCSV(TrueCheckCodes code)
	{
		String outputFile = "codes_"+code.getId()+".csv";
		File file = new File(outputFile);
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			for (Integer i = 1; i <= code.getNoProductBatch(); i++) {
				fileWriter.append(code.getId() + "");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(i + "");
				fileWriter.append(COMMA_DELIMITER);
				String num = RandomStringUtils.randomNumeric(9);
				fileWriter.append(num.toString());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
