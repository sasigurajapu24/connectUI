package com.incon.connect.ui.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_types")

public class UserType implements IEntity {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String usertype;
	
	@Id
	@GeneratedValue
	@Column(name="sno")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="usertype")
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}