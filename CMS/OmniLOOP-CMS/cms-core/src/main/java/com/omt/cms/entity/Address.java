package com.omt.cms.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	@Column(name="address_line1", length=200)
	@Size(max=200)
	private String addressLine1;
	
	@Column(name="address_line2", length=200)
	@Size(max=200)
	private String addressLine2;
	
	@Column(name="address_line3", length=200)
	@Size(max=200)
	private String addressLine3;
	
	@Column(name="address_city", length=200)
	@Size(max=200)
	private String addressCity;
	
	@Column(name="address_state", length=200)
	@Size(max=200)
	private String addressState;
	
	@Column(name="address_country", length=200)
	@Size(max=200)
	private String addressCountry;
	
	@Column(name="address_zip", length=16)
	@Size(max=16)
	private String addressZip;


	public Address(){ }

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}
	
}