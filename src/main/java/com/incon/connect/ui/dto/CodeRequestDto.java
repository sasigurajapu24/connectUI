package com.incon.connect.ui.dto;

public class CodeRequestDto extends PageDto{
	
	private String query;
	private String userName;
	private String companyName;
	private String productName;
	private String batchCode;
	private Long codeId;
	private Integer reqStatus;
	private Long codeReqId;
	

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the batchCode
	 */
	public String getBatchCode() {
		return batchCode;
	}

	/**
	 * @param batchCode the batchCode to set
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	/**
	 * @return the codeId
	 */
	public Long getCodeId() {
		return codeId;
	}

	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	/**
	 * @return the reqStatus
	 */
	public Integer getReqStatus() {
		return reqStatus;
	}

	/**
	 * @param reqStatus the reqStatus to set
	 */
	public void setReqStatus(Integer reqStatus) {
		this.reqStatus = reqStatus;
	}

	/**
	 * @return the codeReqId
	 */
	public Long getCodeReqId() {
		return codeReqId;
	}

	/**
	 * @param codeReqId the codeReqId to set
	 */
	public void setCodeReqId(Long codeReqId) {
		this.codeReqId = codeReqId;
	}

}
