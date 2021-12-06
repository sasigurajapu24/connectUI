package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "code_requests")
public class CodeRequest implements Serializable, IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7001518143851527476L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "code_id")
	private TrueCheckCodes code;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@Column(name = "status")
	private Integer status;
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column(name = "modified_date")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public TrueCheckCodes getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(TrueCheckCodes code) {
		this.code = code;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

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

}
