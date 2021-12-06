package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "offers_details")
public class Offers implements Serializable, IEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "division_id")
	private Long divisionId;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "model_number")
	private String modelNumber;

	@Column(name = "offer")
	private Long offer;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "search_from_date")
	private Date searchFromDate;

	@Column(name = "search_to_date")
	private Date searchToDate;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "merchant_id")
	private Long merchantId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "purchase_id")
	private Long purchaseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Long getOffer() {
		return offer;
	}

	public void setOffer(Long offer) {
		this.offer = offer;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@JsonIgnore
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@JsonIgnore
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Offers [id=" + id + ", brand=" + brandId + ", division=" + divisionId + ", modelNumber=" + modelNumber
				+ ", offer=" + offer + ", fromDate=" + fromDate + ", toDate=" + toDate + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", merchantId=" + merchantId + ", productId=" + productId
				+ ", customerId=" + customerId + ", purchaseId=" + purchaseId + "]";
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@PrePersist
	private void setDefaults() {
		if (null == this.createdDate) {
			this.createdDate = new Date();
		}
	}

	public Date getSearchFromDate() {
		return searchFromDate;
	}

	public void setSearchFromDate(Date searchFromDate) {
		this.searchFromDate = searchFromDate;
	}

	public Date getSearchToDate() {
		return searchToDate;
	}

	public void setSearchToDate(Date searchToDate) {
		this.searchToDate = searchToDate;
	}

}
