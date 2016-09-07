package com.omt.webservice.morningstar.entity;

/**
 * 
 For one code: Same to Paritech value=
 [
 		"D953":"04-01-2013",
 		"D952":"09:51"
        "Cumulative volume": "5308697", //D16
        "Open price": "30.69", //D17
        "High price": "30.79", //D18
        "Low price": "30.44",  //D19
        "Last price": "30.75"  //D2
  {
    "Open": 0.017,
    "Low": 0.017,
    "Close": 0.017,
    "Date": "2012-01-06Z",
    "Volume": 3000,
    "High": 0.017
  },
  ...
 ]
 * 
 * @author tonyliu
 *
 */
public class MsChartHistory {
	private String code;
	private String market;
	private String date;
	private String value;
	private int count;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}

}
