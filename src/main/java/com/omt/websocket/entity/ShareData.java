package com.omt.websocket.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "Code", "Name", "Timestamp", "BestBid", "BestAsk", "BidCount", "BidQuantity", "BidUndisclosed", "AskCount", "AskQuantity", "AskUndisclosed", "High", "Low", "Open", "Close", "ValueChanged", "PercentChange", "Settlement", "Last", "NumberOfTrades", "Volume", "Trend", "Clazz", "Index", "TradingState", "StrikePrice", "CallOrPut", "ContractSize", "SubscriptionData", "AuctionPrice", "AuctionQuantity", "AuctionRemainder", "VWAP", "OpenInterest", "ShareIssue", "Market", "Currency"})

public class ShareData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Code")
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@JsonProperty("BestBid")
	public Double getBestBid() {
		return BestBid;
	}
	public void setBestBid(Double bestBid) {
		BestBid = bestBid;
	}
	@JsonProperty("BestAsk")
	public Double getBestAsk() {
		return BestAsk;
	}
	public void setBestAsk(Double bestAsk) {
		BestAsk = bestAsk;
	}
	@JsonProperty("BidCount")
	public Long getBidCount() {
		return BidCount;
	}
	public void setBidCount(Long bidCount) {
		BidCount = bidCount;
	}
	@JsonProperty("BidQuantity")
	public Long getBidQuantity() {
		return BidQuantity;
	}
	public void setBidQuantity(Long bidQuantity) {
		BidQuantity = bidQuantity;
	}
	@JsonProperty("BidUndisclosed")
	public Boolean isBidUndisclosed() {
		return BidUndisclosed;
	}
	public void setBidUndisclosed(Boolean bidUndisclosed) {
		BidUndisclosed = bidUndisclosed;
	}
	@JsonProperty("AskCount")
	public Long getAskCount() {
		return AskCount;
	}
	public void setAskCount(Long askCount) {
		AskCount = askCount;
	}
	@JsonProperty("AskQuantity")
	public Long getAskQuantity() {
		return AskQuantity;
	}
	public void setAskQuantity(Long askQuantity) {
		AskQuantity = askQuantity;
	}
	@JsonProperty("AskUndisclosed")
	public Boolean isAskUndisclosed() {
		return AskUndisclosed;
	}
	public void setAskUndisclosed(Boolean askUndisclosed) {
		AskUndisclosed = askUndisclosed;
	}
	@JsonProperty("High")
	public Double getHigh() {
		return High;
	}
	public void setHigh(Double high) {
		High = high;
	}
	@JsonProperty("Low")
	public Double getLow() {
		return Low;
	}
	public void setLow(Double low) {
		Low = low;
	}
	@JsonProperty("Open")
	public Double getOpen() {
		return Open;
	}
	public void setOpen(Double open) {
		Open = open;
	}
	@JsonProperty("Close")
	public Double getClose() {
		return Close;
	}
	public void setClose(Double close) {
		Close = close;
	}
	@JsonProperty("Settlement")
	public Double getSettlement() {
		return Settlement;
	}
	public void setSettlement(Double settlement) {
		Settlement = settlement;
	}
	@JsonProperty("Last")
	public Double getLast() {
		return Last;
	}
	public void setLast(Double last) {
		Last = last;
	}
	@JsonProperty("NumberOfTrades")
	public Long getNumberOfTrades() {
		return NumberOfTrades;
	}
	public void setNumberOfTrades(Long numberOfTrades) {
		NumberOfTrades = numberOfTrades;
	}
	@JsonProperty("Volume")
	public Long getVolume() {
		return Volume;
	}
	public void setVolume(Long volume) {
		Volume = volume;
	}
	@JsonProperty("Trend")
	public String getTrend() {
		return Trend;
	}
	public void setTrend(String trend) {
		Trend = trend;
	}
	@JsonProperty("Clazz")
	public String getClazz() {
		return Clazz;
	}
	public void setClazz(String clazz) {
		Clazz = clazz;
	}
	@JsonProperty("Index")
	public Boolean isIndex() {
		return Index;
	}
	public void setIndex(Boolean index) {
		Index = index;
	}
	@JsonProperty("TradingState")
	public String getTradingState() {
		return TradingState;
	}
	public void setTradingState(String tradingState) {
		TradingState = tradingState;
	}
	@JsonProperty("StrikePrice")
	public Double getStrikePrice() {
		return StrikePrice;
	}
	public void setStrikePrice(Double strikePrice) {
		StrikePrice = strikePrice;
	}
	@JsonProperty("CallOrPut")
	public String getCallOrPut() {
		return CallOrPut;
	}
	public void setCallOrPut(String callOrPut) {
		CallOrPut = callOrPut;
	}
	@JsonProperty("ContractSize")
	public Long getContractSize() {
		return ContractSize;
	}
	public void setContractSize(Long contractSize) {
		ContractSize = contractSize;
	}
	@JsonProperty("SubscriptionData")
	public String getSubscriptionData() {
		return SubscriptionData;
	}
	public void setSubscriptionData(String subscriptionData) {
		SubscriptionData = subscriptionData;
	}
	@JsonProperty("AuctionPrice")
	public Double getAuctionPrice() {
		return AuctionPrice;
	}
	public void setAuctionPrice(Double auctionPrice) {
		AuctionPrice = auctionPrice;
	}
	@JsonProperty("AuctionQuantity")
	public Long getAuctionQuantity() {
		return AuctionQuantity;
	}
	public void setAuctionQuantity(Long auctionQuantity) {
		AuctionQuantity = auctionQuantity;
	}
	@JsonProperty("AuctionRemainder")
	public Long getAuctionRemainder() {
		return AuctionRemainder;
	}
	public void setAuctionRemainder(Long auctionRemainder) {
		AuctionRemainder = auctionRemainder;
	}
	@JsonProperty("VWAP")
	public Double getVWAP() {
		return VWAP;
	}
	public void setVWAP(Double vWAP) {
		VWAP = vWAP;
	}
	@JsonProperty("ValueChanged")
	public Double getValueChanged() {
		return ValueChanged;
	}
	public void setValueChanged(Double valueChanged) {
		ValueChanged = valueChanged;
	}
	@JsonProperty("OpenInterest")
	public Long getOpenInterest() {
		return OpenInterest;
	}
	public void setOpenInterest(Long openInterest) {
		OpenInterest = openInterest;
	}
	@JsonProperty("ShareIssue")
	public Long getShareIssue() {
		return ShareIssue;
	}
	public void setShareIssue(Long shareIssue) {
		ShareIssue = shareIssue;
	}
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	@JsonProperty("Market")
	public String getMarket() {
		return Market;
	}
	public void setMarket(String market) {
		Market = market;
	}
	@JsonProperty("Currency")
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	@JsonProperty("Timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@JsonProperty("PercentChange")
	public Double getPercentChange() {
		return PercentChange;
	}
	public void setPercentChange(Double percentChange) {
		PercentChange = percentChange;
	}
	private String Code = null;
	private String timestamp = null;
	private Double PercentChange = null;
	
	   private String Name = null;
	   private Double BestBid = null;
	   private Double BestAsk = null;
	   private Long BidCount = null;
	   private Long BidQuantity = null;
	   private Boolean BidUndisclosed = null;
	   private Long AskCount = null;
	   private Long AskQuantity = null;
	   private Boolean AskUndisclosed = null;
	   private Double High = null;
	   private Double Low = null;
	   private Double Open = null;
	   private Double Close = null;
	   private Double Settlement = null;
	   private Double Last = null;
	   private Long NumberOfTrades = null;
	   private Long Volume = null;
	   private String Trend = null;
	   private String Clazz = null;
	   private Boolean Index = null;
	   private String TradingState = null;
	   private Double StrikePrice = null;
	   private String CallOrPut = null;
	   private Long ContractSize = null;
	   private String SubscriptionData = null;
	   private Double AuctionPrice = null;
	   private Long AuctionQuantity = null;
	   private Long AuctionRemainder = null;
	   private Double VWAP = null;
	   private Double ValueChanged = null;
	   private Long OpenInterest = null;
	   private Long ShareIssue = null;
	   
	   private String Market = null;
	   private String Currency = null;
	}
