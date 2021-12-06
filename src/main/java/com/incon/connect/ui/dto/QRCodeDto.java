package com.incon.connect.ui.dto;

public class QRCodeDto extends PageDto {
	private Integer company;
	private Integer product;

	private Integer codeId;
	private String code;

	private String serialNo;
	private String batchNo;

	private Integer uCodeId;

	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the uCodeId
	 */
	public Integer getuCodeId() {
		return uCodeId;
	}

	/**
	 * @param uCodeId
	 *            the uCodeId to set
	 */
	public void setuCodeId(Integer uCodeId) {
		this.uCodeId = uCodeId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(null != codeId && null != product && null != uCodeId){
			builder.append(codeId).append("-").append(code).append("-").append(product).append("-").append(uCodeId).append("-").append(batchNo);
			return builder.toString();
		}
		return super.toString();
	}

}
