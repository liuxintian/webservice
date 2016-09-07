package com.omt.webservice.entity;

/**
 * This is used for page view plugin easyui.
 * @author tonyliu
 *
 */
public class DataGridModel  implements java.io.Serializable {
	
	private static final long serialVersionUID = 7232798260610351343L;
	private int page; //current page ,name should be:page
	private int rows ; //number items on each page, name should be:rows
	private String sort;  
	private String order;  
	private String filterRules;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getFilterRules() {
		return filterRules;
	}
	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}
	
	
}
