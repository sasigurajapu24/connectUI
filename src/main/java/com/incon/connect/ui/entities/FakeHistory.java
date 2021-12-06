package com.incon.connect.ui.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fake_history")
public class FakeHistory implements Serializable,IEntity{
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue
	    @Column(name = "id")
	    private Long id;
		
		@Column(name = "msisdn")
	    private String msisdn;

		@Column(name = "store_name")
	    private String storeName;
		
		@Column(name = "locality")
	    private String locality;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMsisdn() {
			return msisdn;
		}

		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public String getLocality() {
			return locality;
		}

		public void setLocality(String locality) {
			this.locality = locality;
		}

}
