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
@Table(name = "app_hits")
public class AppHits implements Serializable,IEntity{
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue
	    @Column(name = "id")
	    private Long id;
		
		@Column(name = "code")
	    private String code;
		
		@Column(name = "msisdn")
	    private String msisdn;
		
		@ManyToOne
		@JoinColumn(name = "product_id")
	    private Product product;

		@Column(name = "is_valid")
	    private String isValid;
		
		@Column(name = "insert_timestamp")
	    private Date insertTimestamp;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Date getInsertTimestamp() {
			return insertTimestamp;
		}

		public void setInsertTimestamp(Date insertTimestamp) {
			this.insertTimestamp = insertTimestamp;
		}

		public String getMsisdn() {
			return msisdn;
		}

		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}

		public String getIsValid() {
			return isValid;
		}

		public void setIsValid(String isValid) {
			this.isValid = isValid;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		
		
}
