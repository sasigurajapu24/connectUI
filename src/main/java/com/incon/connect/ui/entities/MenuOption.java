package com.incon.connect.ui.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "menu_options")
@JsonInclude(Include.NON_NULL)
public class MenuOption implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "menu_name")
	private String menuName;

	@Column(name = "module")
	private String module;

	@Column(name = "screen_name")
	private String screenName;

	@Column(name = "action")
	private String action;

	@Column(name = "type")
	private String type;

	@JoinColumn(name = "category_id")
	@ManyToOne
	private ProductCategory category;

	@JoinColumn(name = "division_id")
	@ManyToOne
	private ProductDivision division;
	
	@JoinColumn(name = "brand_id")
	@ManyToOne
	private Brand brand;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Column(name = "created_date")
	private Date createdDate;

	@JoinColumn(name = "parent")
	@ManyToOne
	@JsonBackReference
	private MenuOption parent;
	
	@OneToMany(mappedBy="parent")
	@JsonManagedReference
	private Set<MenuOption> submenu = new HashSet<MenuOption>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@PrePersist
	public void setDefaults() {
		this.createdDate = new Date();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public ProductDivision getDivision() {
		return division;
	}

	public void setDivision(ProductDivision division) {
		this.division = division;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public MenuOption getParent() {
		return parent;
	}

	public void setParent(MenuOption parent) {
		this.parent = parent;
	}

	public Set<MenuOption> getSubmenu() {
		return submenu;
	}

	public void setSubmenu(Set<MenuOption> submenu) {
		this.submenu = submenu;
	}

}
