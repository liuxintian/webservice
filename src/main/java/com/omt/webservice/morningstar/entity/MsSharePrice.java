package com.omt.webservice.morningstar.entity;

/**
 * 
 * @author tonyliu
 *
 */
public class MsSharePrice {


	private String id;    // identity in mongodb
	
    private String name;  // company name
    
    private String datetime; // record time 
    
    private ShareDataM data;  // record data
    
    private String market;
    
    private boolean dataChangedAll;
    
	private boolean dataChangedLast;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ShareDataM getData() {
		return data;
	}

	public void setData(ShareDataM data) {
		this.data = data;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getName() {
        return name;
    }

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

    public boolean isDataChangedAll() {
		return dataChangedAll;
	}

	public void setDataChangedAll(boolean dataChangedAll) {
		this.dataChangedAll = dataChangedAll;
	}

	public boolean isDataChangedLast() {
		return dataChangedLast;
	}

	public void setDataChangedLast(boolean dataChangedLast) {
		this.dataChangedLast = dataChangedLast;
	}

}
