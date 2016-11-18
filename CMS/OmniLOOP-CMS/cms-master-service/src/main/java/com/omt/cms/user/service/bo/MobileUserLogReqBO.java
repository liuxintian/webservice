package com.omt.cms.user.service.bo;

public class MobileUserLogReqBO {

	private long start;
	private long end;
	
	MobileUserLogReqBO(){ }

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
}
