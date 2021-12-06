package com.incon.connect.ui.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "true_check_codes")
public class TrueCheckCodes {
	
	@Id
    @Basic(optional = false)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "batch_code")
	private String batchCode;
	@Column(name = "no_product_batch")
	private Integer noProductBatch;
	@Column(name = "mfg_date")
	@Temporal(TemporalType.DATE)
	private Date mfgDate;
	@Column(name = "exp_date")
	@Temporal(TemporalType.DATE)
	private Date expDate;
	@Column(name = "product_specification")
	private String productSpecification;
	@Column(name = "thanks_msg")
	private String thanksMsg;
	@Column(name = "rewards")
	private String rewards;
	@Column(name = "promotion_msg")
	private String promotionMsg;
	@Column(name = "special_instruction")
	private String specialInstruction;
	@Column(name = "suggestions")
	private String suggestions;
	@Column(name = "contactinfo")
	private String contactInfo;
	@Column(name = "warranty")
	private String warranty;
	@Column(name = "feedback")
	private String feedback;
	@Column(name = "review")
	private String review;
	@Column(name = "bill")
	private String bill;
	@Column(name = "status")
	private Integer status = 0;
	@Column(name = "created_by")
	private Long createdBy;
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "price")
	private Double price;
	 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public Integer getNoProductBatch() {
		return noProductBatch;
	}
	public void setNoProductBatch(Integer noProductBatch) {
		this.noProductBatch = noProductBatch;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getProductSpecification() {
		return productSpecification;
	}
	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}
	public String getThanksMsg() {
		return thanksMsg;
	}
	public void setThanksMsg(String thanksMsg) {
		this.thanksMsg = thanksMsg;
	}
	public String getRewards() {
		return rewards;
	}
	public void setRewards(String rewards) {
		this.rewards = rewards;
	}
	
	public String getPromotionMsg() {
		return promotionMsg;
	}
	public void setPromotionMsg(String promotionMsg) {
		this.promotionMsg = promotionMsg;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

	@PreUpdate
	public void preUpdate() {
		modifiedDate = new Date();
	}

	@PrePersist
	public void prePersist() {
		Date now = new Date();
		createdDate = now;
		modifiedDate = now;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	

}
