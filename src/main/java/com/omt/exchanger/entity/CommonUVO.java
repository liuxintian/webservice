package com.omt.exchanger.entity;

/**
 * common sense of result
 * @author tonyliu
 *
 */
public class CommonUVO {

	private String text; // result code;
	
	private String value; // result value;
	
	private boolean selected = false; 

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
