package com.omt.cms.core.service.bo.base;

public class AddressBO {

	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressCity;
	private String addressState;
	private String addressCountry;
	private String addressZip;

	public AddressBO(){ }

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
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(addressLine1);
		builder.append(" ");
		builder.append(addressLine2);
		builder.append(" ");
		builder.append(addressLine3);
		builder.append(" ");
		builder.append(addressCity);
		builder.append(" ");
		builder.append(addressState);
		builder.append(" ");
		builder.append(addressCountry);
		builder.append(" ");
		builder.append(addressZip);
		return builder.toString();
	}
}