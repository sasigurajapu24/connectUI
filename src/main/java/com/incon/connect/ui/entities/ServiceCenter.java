/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author SRIRAM
 */
@Entity
@Table(name = "service_center")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ServiceCenter.findAll", query = "SELECT s FROM ServiceCenter s")
//    , @NamedQuery(name = "ServiceCenter.findById", query = "SELECT s FROM ServiceCenter s WHERE s.id = :id")
//    , @NamedQuery(name = "ServiceCenter.findByName", query = "SELECT s FROM ServiceCenter s WHERE s.name = :name")
//    , @NamedQuery(name = "ServiceCenter.findByLocation", query = "SELECT s FROM ServiceCenter s WHERE s.location = :location")
//    , @NamedQuery(name = "ServiceCenter.findByCategoryId", query = "SELECT s FROM ServiceCenter s WHERE s.categoryId = :categoryId")
//    , @NamedQuery(name = "ServiceCenter.findByDivisionId", query = "SELECT s FROM ServiceCenter s WHERE s.divisionId = :divisionId")
//    , @NamedQuery(name = "ServiceCenter.findByBrandId", query = "SELECT s FROM ServiceCenter s WHERE s.brandId = :brandId")
//    , @NamedQuery(name = "ServiceCenter.findByContactNo", query = "SELECT s FROM ServiceCenter s WHERE s.contactNo = :contactNo")
//    , @NamedQuery(name = "ServiceCenter.findByEmail", query = "SELECT s FROM ServiceCenter s WHERE s.email = :email")
//    , @NamedQuery(name = "ServiceCenter.findByCreatedBy", query = "SELECT s FROM ServiceCenter s WHERE s.createdBy = :createdBy")
//    , @NamedQuery(name = "ServiceCenter.findByCreatedDate", query = "SELECT s FROM ServiceCenter s WHERE s.createdDate = :createdDate")})
public class ServiceCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Transient
    private String address;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "division_id")
    private Integer divisionId;
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "email")
    private String email;
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "gstn")
    private String gstn;
    @Column(name = "logo")
    private byte[] logo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address addressInfo;
    
    @Transient
    private String logoUrl;

    public ServiceCenter() {
    }

    public ServiceCenter(Integer id) {
        this.id = id;
    }

    public ServiceCenter(Integer id, Date createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceCenter)) {
            return false;
        }
        ServiceCenter other = (ServiceCenter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "awsclient.ServiceCenter[ id=" + id + " ]";
    }

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}
	
	
	@PrePersist
	public void setInsertValues(){
		this.createdDate = new Date();
	}

	public Address getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(Address addressInfo) {
		this.addressInfo = addressInfo;
	}

	@JsonIgnore
	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	@PostLoad
	public void setValue(){
		this.logoUrl = "/service/logo/"+this.id;
	}
}
