package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "brands")
public class Brand implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory category;

	@ManyToOne
	@JoinColumn(name = "division_id")
	private ProductDivision division;

	@Column(name = "insert_timestamp")
	private Date insertTimestamp;

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
	 * @return the insertTimestamp
	 */
	public Date getInsertTimestamp() {
		return insertTimestamp;
	}

	/**
	 * @param insertTimestamp
	 *            the insertTimestamp to set
	 */
	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
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

	public ProductDivision getDivision() {
		return division;
	}

	public void setDivision(ProductDivision division) {
		this.division = division;
	}
	
	@PrePersist
	public void setDefaults(){
		
		if(null == insertTimestamp){
			this.insertTimestamp = new Date();
		}
	}


}
