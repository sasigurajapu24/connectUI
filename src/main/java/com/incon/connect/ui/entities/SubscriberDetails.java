package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "subscriber_details")
@JsonInclude(Include.NON_NULL)
public class SubscriberDetails implements IEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -40975819187854607L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "location")
	private String location;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "usertype")
	private Integer usertype;

	@Column(name = "insert_timestamp")
	private Date insertTimestamp;

	@Column(name = "otp")
	private String otp;

	@Column(name = "otp_status")
	private Integer otpStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id")
	StoreDetails store;

	@ManyToOne
	@JoinColumn(name = "srvc_ctr_id")
	private ServiceCenter serviceCenter;

	@Transient
	private String password;

	@Column(name = "password")
	private String passwordStr;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "temp_user")
	private Integer tempUser;

	@Column(name = "country")
	private String country;

	@Column(name = "approve_status")
	private Integer approveStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@JsonIgnore
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@JsonIgnore
	public Date getInsertTimestamp() {
		return insertTimestamp;
	}

	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the password
	 */
	@Transient
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the usertype
	 */
	public Integer getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype
	 *            the usertype to set
	 */
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	/**
	 * @return the otpStatus
	 */
	@JsonIgnore
	public Integer getOtpStatus() {
		return otpStatus;
	}

	/**
	 * @param otpStatus
	 *            the otpStatus to set
	 */
	public void setOtpStatus(Integer otpStatus) {
		this.otpStatus = otpStatus;
	}

	/**
	 * @return the passswordStr
	 */
	@JsonIgnore
	public String getPasswordStr() {
		return passwordStr;
	}

	/**
	 * @param passswordStr
	 *            the passswordStr to set
	 */
	public void setPasswordStr(String passwordStr) {
		this.passwordStr = passwordStr;
	}

	@PrePersist
	public void setPasswordOnPersist() {
		this.passwordStr = this.password;
		if (StringUtils.isEmpty(this.passwordStr)) {
			this.passwordStr = RandomStringUtils.randomNumeric(8);
		}
		if (null == country) {
			country = "India";
		}
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

	/**
	 * @return the tempUser
	 */
	public Integer getTempUser() {
		return tempUser;
	}

	/**
	 * @param tempUser
	 *            the tempUser to set
	 */
	public void setTempUser(Integer tempUser) {
		this.tempUser = tempUser;
	}

	/**
	 * @return the store
	 */
	public StoreDetails getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(StoreDetails store) {
		this.store = store;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	/**
	 * 9
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

}
