package com.omt.webservice.morningstar.entity;

public class Instrument {

	private String market;
	private String exchange;
	private String sectype;
	private String symbol;
	
	private String symbolname;

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSectype() {
		return sectype;
	}

	public void setSectype(String sectype) {
		this.sectype = sectype;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getInstrument() {
		return exchange+"."+sectype+"."+symbol;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getSymbolname() {
		return symbolname;
	}

	public void setSymbolname(String symbolname) {
		this.symbolname = symbolname;
	}
}
