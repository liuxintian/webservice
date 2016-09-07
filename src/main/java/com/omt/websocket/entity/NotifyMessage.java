package com.omt.websocket.entity;

/**
 * Input push message from companies
 * @author tonyliu
 *
 */
public class NotifyMessage implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String id;    // identity in mongodb
	
    private String name;  // company name
    
    private String market; // market
    
    private String datetime; // record time 
    
    private ShareData data;  // record data
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ShareData getData() {
		return data;
	}

	public void setData(ShareData data) {
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

}
