/**
 * 
 */
package com.omt.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * @author muragesh
 *
 */
@Entity
@Table(name = "system_properties")
@SequenceGenerator(initialValue = 1, name = "SEQGEN", sequenceName = "system_properties_seq")
public class SystemProperties {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQGEN")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="value",length=50)
	@Size(max=50)
	private String value;
	
	@Column(name="category")
	private Integer category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

		
	public Long getId() {
		return id;
	}


	public enum Category{
		TAG_ROLES(1);
		
		private int value;
		
		private Category(int v){
			this.value=v;
		}
		
		public int getValue(){
			return this.value;
		}
		
	}
}
