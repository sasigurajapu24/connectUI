package com.incon.connect.ui.dto;

import java.io.Serializable;

public class ValidateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String thanksMessage;
	
	private boolean isValid;
	
	private String offerMessage;
	
	private String totalPointsMessage;
	
	private String pointsReqMessage;
	
	private Integer pointsEarned;
	
	private Integer pointsRequired;
	
	private String targetReachedMessage;

	public String getThanksMessage() {
		return thanksMessage;
	}

	public void setThanksMessage(String thanksMessage) {
		this.thanksMessage = thanksMessage;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getOfferMessage() {
		return offerMessage;
	}

	public void setOfferMessage(String offerMessage) {
		this.offerMessage = offerMessage;
	}

	public String getTotalPointsMessage() {
		return totalPointsMessage;
	}

	public void setTotalPointsMessage(String totalPointsMessage) {
		this.totalPointsMessage = totalPointsMessage;
	}

	public String getPointsReqMessage() {
		return pointsReqMessage;
	}

	public void setPointsReqMessage(String pointsReqMessage) {
		this.pointsReqMessage = pointsReqMessage;
	}

	public Integer getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public Integer getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(Integer pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	public String getTargetReachedMessage() {
		return targetReachedMessage;
	}

	public void setTargetReachedMessage(String targetReachedMessage) {
		this.targetReachedMessage = targetReachedMessage;
	} 
	

}
