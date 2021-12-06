package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_details")
public class Product implements Serializable, IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	// @NotBlank(message = "Name cannot be empty")
	@Column(name = "name")
	private String name;

	@Column(name = "logo")
	private byte[] logo;

	@Column(name = "information")
	private String information;
	@Column(name = "directions")
	private String directions;
	@Column(name = "warnings")
	private String warnings;
	@Column(name = "brandinfo")
	private String brandinfo;
	@Column(name = "points")
	private Integer points;
	@Column(name = "points_msg")
	private String pointsMsg;
	@Column(name = "target_points")
	private Integer targetPoints;
	@Column(name = "target_msg")
	private String targetMsg;
	@Column(name = "warranty")
	private String warranty;
	@Column(name = "return_policy")
	private String returnPolicy;
	@Column(name = "model_number")
	private String modelNumber;
	@Column(name = "entry_point")
	private Integer entryPoint;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory category;
	@ManyToOne
	@JoinColumn(name = "division_id")
	private ProductDivision division;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "productId")
	// @JoinColumn(name = "product_id")
	private List<ProductOffers> offers;
	@Transient
	private String productCode;
	@Column(name = "color")
	private String color;
	@Column(name = "size")
	private String size;
	@Column(name = "price")
	private Double price;
	@JoinColumn(name = "mrp")
	private Double mrp;
	@Transient
	private String item;

	@Column(name = "image")
	private byte[] image;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "warranty_years")
	private Long warrantyYears;
	@Column(name = "warranty_months")
	private Long warrantyMonths;
	@Column(name = "warranty_days")
	private Long warrantyDays;
	@Column(name = "mfg_date")
	@Temporal(TemporalType.DATE)
	private Date mfgDate;
	@Column(name = "exp_date")
	@Temporal(TemporalType.DATE)
	private Date expDate;
	@Column(name = "product_specification")
	private String productSpecification;
	@Column(name = "special_instruction")
	private String specialInstruction;
	
	private String customerCare;

	@Transient
	private MultipartFile file;

	@Transient
	private String logoUrl;
	@Transient
	private String imageUrl;

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

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param information
	 *            the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * @return the directions
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * @param directions
	 *            the directions to set
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	/**
	 * @return the warnings
	 */
	public String getWarnings() {
		return warnings;
	}

	/**
	 * @param warnings
	 *            the warnings to set
	 */
	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@JsonIgnore
	public String getPointsMsg() {
		return pointsMsg;
	}

	public void setPointsMsg(String pointsMsg) {
		this.pointsMsg = pointsMsg;
	}

	@JsonIgnore
	public Integer getTargetPoints() {
		return targetPoints;
	}

	public void setTargetPoints(Integer targetPoints) {
		this.targetPoints = targetPoints;
	}

	@JsonIgnore
	public String getTargetMsg() {
		return targetMsg;
	}

	public void setTargetMsg(String targetMsg) {
		this.targetMsg = targetMsg;
	}

	/**
	 * @return the createdDate
	 */
	@JsonIgnore
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the warranty
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * @param warranty
	 *            the warranty to set
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	/**
	 * @return the imageBlob
	 */
	public byte[] getImage() {
		return image;
	}

//	/**
//	 * @param imageBlob
//	 *            the imageBlob to set
//	 */
//	public void setImageBlob(byte[] image) {
//		this.image = image;
//	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl
	 *            the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@PostLoad
	public void updateImages() {
		this.logoUrl = "/products/logo/" + getId();
		this.imageUrl = "/products/image/" + getId();
		this.item = this.division != null ? this.division.getName() : "Shirt";
	}

	/**
	 * @return the productCode
	 */
	@Transient
	public String getProductCode() {
		return this.name + this.id;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
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
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the category
	 */
	public ProductCategory getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	/**
	 * @return the division
	 */
	public ProductDivision getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(ProductDivision division) {
		this.division = division;
	}

	/**
	 * @return the brandinfo
	 */
	public String getBrandinfo() {
		return brandinfo;
	}

	/**
	 * @param brandinfo
	 *            the brandinfo to set
	 */
	public void setBrandinfo(String brandinfo) {
		this.brandinfo = brandinfo;
	}

	/**
	 * @return the returnPolicy
	 */
	public String getReturnPolicy() {
		return returnPolicy;
	}

	/**
	 * @param returnPolicy
	 *            the returnPolicy to set
	 */
	public void setReturnPolicy(String returnPolicy) {
		this.returnPolicy = returnPolicy;
	}

	/**
	 * @return the offers
	 */
	@JsonIgnore
	public List<ProductOffers> getOffers() {
		return offers;
	}

	/**
	 * @param offers
	 *            the offers to set
	 */
	public void setOffers(List<ProductOffers> offers) {
		this.offers = offers;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Integer getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(Integer entryPoint) {
		this.entryPoint = entryPoint;
	}

	public Long getWarrantyYears() {
		return warrantyYears;
	}

	public void setWarrantyYears(Long warrantyYears) {
		this.warrantyYears = warrantyYears;
	}

	public Long getWarrantyMonths() {
		return warrantyMonths;
	}

	public void setWarrantyMonths(Long warrantyMonths) {
		this.warrantyMonths = warrantyMonths;
	}

	public Long getWarrantyDays() {
		return warrantyDays;
	}

	public void setWarrantyDays(Long warrantyDays) {
		this.warrantyDays = warrantyDays;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getCustomerCare() {
		return customerCare;
	}

	public void setCustomerCare(String customerCare) {
		this.customerCare = customerCare;
	}
	
	@PrePersist
	public void setDefaults(){
		
		if(null == createdDate){
			this.createdDate = new Date();
		}
	}

}
