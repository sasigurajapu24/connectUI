package com.incon.connect.ui.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "master_codes")
public class MasterCodes implements Serializable,IEntity{
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue
	    @Column(name = "id")
	    private Long id;

		@Column(name = "category")
	    private String category;
		
		@Column(name = "type")
	    private String type;
		
		@Column(name = "value")
	    private String value;

		@Column(name = "insert_timestamp")
	    private Date insertTimestamp;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getInsertTimestamp() {
			return insertTimestamp;
		}

		public void setInsertTimestamp(Date insertTimestamp) {
			this.insertTimestamp = insertTimestamp;
		}

		/**
		 * @return the category
		 */
		public String getCategory() {
			return category;
		}

		/**
		 * @param category the category to set
		 */
		public void setCategory(String category) {
			this.category = category;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

		
		
}
