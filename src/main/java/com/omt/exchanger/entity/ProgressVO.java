package com.omt.exchanger.entity;

public class ProgressVO {
	private long datetime;
	private String progress;
	
	private int total;
	private int done;
	public long getDatetime() {
		return datetime;
	}
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}
	
}
