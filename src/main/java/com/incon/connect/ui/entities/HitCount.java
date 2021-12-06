package com.incon.connect.ui.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hits_count")
public class HitCount implements Serializable,IEntity{

	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue
	    @Column(name = "id")
	    private Long id;
		
		@Column(name = "code")
	    private String code;
		
		@Column(name = "valid_count")
	    private Integer validCount;

		@Column(name = "fake_count")
	    private Integer fakeCount;

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

		public Integer getValidCount() {
			return validCount;
		}

		public void setValidCount(Integer validCount) {
			this.validCount = validCount;
		}

		public Integer getFakeCount() {
			return fakeCount;
		}

		public void setFakeCount(Integer fakeCount) {
			this.fakeCount = fakeCount;
		}
		
}
