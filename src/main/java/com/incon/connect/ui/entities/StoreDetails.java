package com.incon.connect.ui.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.incon.connect.ui.Application;
import com.incon.connect.ui.util.MyUtils;

@Entity
@Table(name = "store_Details")
public class StoreDetails implements IEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

		
	@Column(name="created_by")
	private Long createdBy;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the area
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		try {
//			Application.mapper.writeValueAsString(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.toString();
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
	
	
	@PrePersist
	public void setPasswordOnPersist() {
		if(null == createdBy){
			createdBy = MyUtils.getAuth() != null ? MyUtils.getAuth().getId() : 1l ;
		}
	}
}
