package com.omt.webservice.morningstar.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.omt.websocket.WebSocketClient;

/**
 * http://stackoverflow.com/questions/8560348/different-names-of-json-property-during-serialization-and-deserialization
 * @author tonyliu
 *
 */
@JsonPropertyOrder({ "Code", "Name", "Timestamp", "BestBid", "BestAsk", "BidCount", "BidQuantity", "BidUndisclosed", "AskCount", "AskQuantity", "AskUndisclosed", "High", "Low", "Open", "Close", "ValueChanged", "PercentChange", "Settlement", "Last", "NumberOfTrades", "Volume", "Trend", "Clazz", "Index", "TradingState", "StrikePrice", "CallOrPut", "ContractSize", "SubscriptionData", "AuctionPrice", "AuctionQuantity", "AuctionRemainder", "VWAP", "OpenInterest", "ShareIssue", "Market", "Currency"})
public class ShareDataM implements Serializable {

	@JsonProperty("Code")
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	@JsonProperty("H1")
	public void setH1(String code) {
		Code = code;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("S12")
	public void setS12(String name) {
		if(Name == null || Name.trim().length() == 0) Name = name;
	}

	@JsonProperty("S1734")
	public void setS1734(String name) {
		if(name != null && name.trim().length() > 0) Name = name;
	}
	
	@JsonProperty("S3377")
	public void setS3377(String name) {
		if(name != null && name.trim().length() > 0) Name = name;
	}
	
	@JsonProperty("BestBid")
	public Double getBestBid() {
		return BestBid;
	}
	public void setBestBid(Double bestBid) {
		BestBid = bestBid;
	}
	@JsonProperty("D4")
	public void setD4(Double bestBid) {
		BestBid = bestBid;
	}
	
	@JsonProperty("BestAsk")
	public Double getBestAsk() {
		return BestAsk;
	}
	public void setBestAsk(Double bestAsk) {
		BestAsk = bestAsk;
	}
	@JsonProperty("D6")
	public void setD6(Double bestAsk) {
		BestAsk = bestAsk;
	}
	
	@JsonProperty("BidCount")
	public Long getBidCount() {
		return BidCount;
	}
	public void setBidCount(Long bidCount) {
		BidCount = bidCount;
	}
	@JsonProperty("D167")
	public void setD167(Long bidCount) {
		BidCount = bidCount;
	}
	
	@JsonProperty("BidQuantity")
	public Long getBidQuantity() {
		return BidQuantity;
	}
	public void setBidQuantity(Long bidQuantity) {
		BidQuantity = bidQuantity;
	}
	@JsonProperty("D5")
	public void setD5(Long bidQuantity) {
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
	@JsonProperty("D185")
	public void setD185(Long askCount) {
		AskCount = askCount;
	}
	
	@JsonProperty("AskQuantity")
	public Long getAskQuantity() {
		return AskQuantity;
	}
	public void setAskQuantity(Long askQuantity) {
		AskQuantity = askQuantity;
	}
	@JsonProperty("D7")
	public void setD7(Long askQuantity) {
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
	@JsonProperty("D18")
	public void setD18(Double high) {
		High = high;
	}
	
	@JsonProperty("Low")
	public Double getLow() {
		return Low;
	}
	public void setLow(Double low) {
		Low = low;
	}
	@JsonProperty("D19")
	public void setD19(Double low) {
		Low = low;
	}
	
	@JsonProperty("Open")
	public Double getOpen() {
		return Open;
	}
	public void setOpen(Double open) {
		Open = open;
	}
	@JsonProperty("D17")
	public void setD17(Double open) {
		Open = open;
	}
	 
	@JsonProperty("Close")
	public Double getClose() {
		return Close;
	}
	public void setClose(Double close) {
		Close = close;
	}
	@JsonProperty("D20")
	public void setD20(Double close) {
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
	@JsonProperty("D2")
	public void setD2(Double last) {
		Last = last;
	}
	
	@JsonProperty("NumberOfTrades")
	public Long getNumberOfTrades() {
		return NumberOfTrades;
	}
	public void setNumberOfTrades(Long numberOfTrades) {
		NumberOfTrades = numberOfTrades;
	}
	@JsonProperty("D30")
	public void setD30(Long numberOfTrades) {
		NumberOfTrades = numberOfTrades;
	}
	
	@JsonProperty("Volume")
	public Long getVolume() {
		return Volume;
	}
	public void setVolume(Long volume) {
		Volume = volume;
	}
	@JsonProperty("D16")
	public void setD16(Long volume) {
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
	@JsonProperty("S8")
	public void setS8(Long contractSize) {
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
	@JsonProperty("D140")
	public void setD140(Double auctionPrice) {
		AuctionPrice = auctionPrice;
	}
	@JsonProperty("AuctionQuantity")
	public Long getAuctionQuantity() {
		return AuctionQuantity;
	}
	public void setAuctionQuantity(Long auctionQuantity) {
		AuctionQuantity = auctionQuantity;
	}
	@JsonProperty("D141")
	public void setD141(Long auctionQuantity) {
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
	@JsonProperty("D243")
	public void setD243(Double vWAP) {
		VWAP = vWAP;
	}
	
	@JsonProperty("ValueChanged")
	public Double getValueChanged() {
		return ValueChanged;
	}
	public void setValueChanged(Double valueChanged) {
		ValueChanged = valueChanged;
	}
	@JsonProperty("H8")
	public void setH8(Double valueChanged) {
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
	@JsonProperty("S1736")
	public void setS1736(String market) {
		if(market != null && market.trim().length() > 0) Market = market;
	}
	
	@JsonProperty("Currency")
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	@JsonProperty("S9")
	public void setS9(String currency) {
		Currency = currency;
	}
	
	@JsonProperty("Timestamp")
	public String getTimestamp() {
		return timestamp;
//		StringBuffer tt = new StringBuffer();
//		if(updateDate != null && updateTime != null){
//			tt.append(updateDate.replaceAll("-", "/")).append(" ").append(updateTime.replaceAll(".000", ""));		
//		}
//		return tt.toString();
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
	@JsonProperty("H14")
	public void setH14(Double percentChange) {
		PercentChange = percentChange;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//"H1" (Symbol) =CSCO
	// @JsonProperty("H1")
	private String Code = null;
	
	private String timestamp = null;
	
	// S1734 Company name
	// @JsonProperty("S1734")
	private String Name = null;
	// D10 Best Bid Price
	// @JsonProperty("D4")
	private Double BestBid = null;
	// D14 Best Ask Price
	// @JsonProperty("D6")
	private Double BestAsk = null;
	// D8 Bid Size
	// @JsonProperty("D167")
	private Long BidCount = null;
	// D5 Bid Market
	// @JsonProperty("D5")
	private Long BidQuantity = null;
	// 
	private Boolean BidUndisclosed = null;
	// D7 Ask Size
	// @JsonProperty("D185")
	private Long AskCount = null;
	// D12 Ask Market
	// @JsonProperty("D7")
	private Long AskQuantity = null;
	//
	private Boolean AskUndisclosed = null;
	// D17 opening price (within the bar)
	// @JsonProperty("D17")
	private Double Open = null;
	// D18 high price (within the bar)
	// @JsonProperty("D18")
	private Double High = null;
	// D19 low price (within the bar)
	// @JsonProperty("D19")
	private Double Low = null;
	// D20 Close price
	// @JsonProperty("D20")
	private Double Close = null;
	// 
	private Double Settlement = null;
	// D2 Last Price
	// @JsonProperty("D2")
	private Double Last = null;
	// D30 # of trades since ma
	// @JsonProperty("D30")
	private Long NumberOfTrades = null;
	// D16 volume
	// @JsonProperty("D16")
	private Long Volume = null;
	// 
	private String Trend = null;
	// 
	private String Clazz = null;
	// 
	private Boolean Index = null;
	// 
	private String TradingState = null;
	// 
	private Double StrikePrice = null;
	// 
	private String CallOrPut = null;
	// S8 Contract size (share
	// @JsonProperty("S8")
	private Long ContractSize = null;
	// 
	private String SubscriptionData = null;
	// D140 Theoretical price
	// @JsonProperty("D140")
	private Double AuctionPrice = null;
	// D141 Wholesale volume/The
	// @JsonProperty("D141")
	private Long AuctionQuantity = null;
	// 
	private Long AuctionRemainder = null;
	// D243 VWAP price
	// @JsonProperty("D243")
	private Double VWAP = null;
	// H14 Net change %
	// @JsonProperty("H14")
	private Double PercentChange = null;
	// H8 Net Change (D2 – D20)
	// @JsonProperty("H8")
	private Double ValueChanged = null;
	//
	private Long OpenInterest = null;
	// 
	private Long ShareIssue = null;
	// MS Listing Exchange 
	// @JsonProperty("S1736")
	private String Market = WebSocketClient.DEFAULT_MARKT;
	// Listed Currency
	// @JsonProperty("S9")
	private String Currency = null;
	

	public String toTriggerString(){
		StringBuffer str = new StringBuffer();
		str.append(getLast()).append("--")
		   .append(getHigh()).append("--")
		   .append(getLow()).append("--")
		   .append(getVolume()).append("--")
		   .append(getClose());
		//System.out.println("ShareData:"+str.toString());
		return str.toString();
	}
/**

<message symbol="126.1.CSCO">

<fld id="H1"> (Symbol)  = CSCO</fld>

<fld id="H2"> (Exchange)  = 126</fld>

<fld id="H3"> (Security Type)  = 1</fld>

<fld id="H8"> (Net Change)  = 0.255</fld>

<fld id="H14"> (% net Change)  = 0.9574</fld>

<fld id="H15"> (Adjusted % net change)  = 0.255</fld>

<fld id="H16"> (Adjusted net change)  = 0.9574</fld>

<fld id="D1"> (Time in minutes of the day (0..1440))  = 1261</fld>

<fld id="D2"> (Last price)  = 26.89</fld>

<fld id="D3"> (Last volume)  = 2384234</fld>

<fld id="D4"> (Bid price)  = 0</fld>

<fld id="D5"> (Bid size)  = 0</fld>

<fld id="D6"> (Ask price)  = 0</fld>

<fld id="D7"> (Ask size)  = 0</fld>

<fld id="D8"> (Bid market)  = 19</fld>

<fld id="D12"> (Ask market)  = 19</fld>

<fld id="D16"> (Cumulative volume)  = 18966280</fld>

<fld id="D17"> (Open price)  = 26.85</fld>

<fld id="D18"> (High price)  = 26.9</fld>

<fld id="D19"> (Low price)  = 26.555</fld>

<fld id="D20"> (Yesterday's close price)  = 26.635</fld>

<fld id="D30"> (# of trades since market opening)  = 60000</fld>

<fld id="D48"> (Close qualifier (for IBIS(213): broker, for F)  = 57600218157</fld>

<fld id="D49"> (Spot qualifier (for IBIS(213): broker, for FF)  = 57600217847</fld>

<fld id="D50"> (Last qualifier (for IBIS(213): broker, for FF)  = 57599319522</fld>

<fld id="D108"> (Bit flag to updated last/high/low/open/vol/c)  = 4</fld>

<fld id="D114"> (Bid indicator flag)  = R</fld>

<fld id="D115"> (Ask indicator flag)  = R</fld>

<fld id="D138"> (Trade indicator flag)  = @6_X</fld>

<fld id="D203"> (Origin market)  = Q</fld>

<fld id="D214"> (Origination market of last trade for composi)  = 19</fld>

<fld id="D243"> (VWAP price (frac))  = 26.74</fld>

<fld id="D417"> (Close bid)  = 26.88</fld>

<fld id="D418"> (Close ask)  = 26.89</fld>

<fld id="D423"> (Calculated Turnover)  = 484115186.38290095</fld>

<fld id="D502"> (Original Trade time (Trace) - ms since midni)  = 57600218</fld>

<fld id="D768"> (Bid, Time of last update inc seconds)  = 20:15:01.000</fld>

<fld id="D769"> (Ask, Time of last update inc seconds)  = 20:15:01.000</fld>

<fld id="D770"> (Last, Time of last update inc seconds)  = 16:15:01.000</fld>

<fld id="D771"> (Today's close)  = 26.89</fld>

<fld id="D772"> (Time of update to today's close inc seconds)  = 21:16:02.000</fld>

<fld id="D773"> (Date of update to today's close inc seconds)  = 22-12-2015</fld>

<fld id="D774"> (Trade before main trading session)  = 26.79</fld>

<fld id="D775"> (Time of trade before main trading session)  = 09:44:59.000</fld>

<fld id="D776"> (Trade after main trading session)  = 26.95</fld>

<fld id="D777"> (Time of trade after main trading session)  = 20:04:38.000</fld>

<fld id="D781"> (Market Centre)  = 0</fld>

<fld id="D782"> (Last update date of any realtime field)  = 22-12-2015</fld>

<fld id="D783"> (Last update time of any realtime field)  = 21:16:02.000</fld>

<fld id="D784"> (Date of last trade)  = 22-12-2015</fld>

<fld id="D785"> (Reserved for internal snapper usage)  = 22-12-2015</fld>

<fld id="D791"> (Bid Date)  = 22-12-2015</fld>

<fld id="D792"> (Ask Date)  = 22-12-2015</fld>

<fld id="D793"> (Non Zero Bid)  = 25.87</fld>

<fld id="D794"> (Non Zero Ask)  = 27.31</fld>

<fld id="D798"> (Internal Snapper use only)  = 0</fld>

<fld id="D799"> (Yesterday's Close Date)  = 22-12-2015</fld>

<fld id="D905"> (NAV Status (Internal snapper only))  = 0</fld>

<fld id="D933"> (Close Date)  = 22-12-2015</fld>

<fld id="D934"> (Pretrade Date)  = 22-12-2015</fld>

<fld id="D935"> (Posttrade Date)  = 22-12-2015</fld>

<fld id="D937"> (Total Spread Value)  = 283.62</fld>

<fld id="D938"> (Average Spread Value)  = 0.1024268689</fld>

<fld id="D939"> (Number of Spread updates)  = 2769</fld>

<fld id="D940"> (RT of Bid in Seconds (EST) (MCR))  = 20:00:00.000</fld>

<fld id="D941"> (RT of Ask in Seconds (EST) (MCR))  = 20:00:00.000</fld>

<fld id="D942"> (RT of Last Trade in Seconds (EST) MCR))  = 16:00:00.000</fld>

<fld id="D943"> (RT of last update in Seconds (EST) (MCR))  = 20:00:00.000</fld>

<fld id="D945"> (Total Relative spread value)  = 10.6368669162</fld>

<fld id="D946"> (Average relative spread value)  = 0.0038414109</fld>

<fld id="D947"> (Time of last close message - Snapper)  = 21:16:02.000</fld>

<fld id="D948"> (Pre Trade Cumulative Volume)  = 10437</fld>

<fld id="D949"> (Post Trade Cumulative Volume)  = 312004</fld>

<fld id="D950"> (Post Trade net Change)  = 0.05</fld>

<fld id="D951"> (Pre Trade net change)  = 0.15</fld>

<fld id="D954"> (Instrument Trading Status (Closed, Pre-open,)  = Closed</fld>

<fld id="D955"> (Todays close price in close message)  = 26.89</fld>

<fld id="D956"> (Time of last update to today's close in clos)  = 21:16:02.000</fld>

<fld id="D957"> (Date of last update to today's close in clos)  = 22-12-2015</fld>

<fld id="D958"> (Date of Last non Zero Bid)  = 22-12-2015</fld>

<fld id="D959"> (Date of Last non Zero Ask)  = 22-12-2015</fld>

<fld id="D960"> (Time of Last non Zero Bid)  = 20:15:01.000</fld>

<fld id="D961"> (Time of Last non Zero Ask)  = 20:15:01.000</fld>

<fld id="D964"> (Julian Date of last Bid (EST) (MCR))  = 22-12-2015</fld>

<fld id="D965"> (Julian Date of last Ask (EST) (MCR))  = 22-12-2015</fld>

<fld id="D966"> (Julian Date of last Trade (EST) (MCR))  = 22-12-2015</fld>

<fld id="D967"> (Total Spread while instrument is open)  = 18.66</fld>

<fld id="D968"> (Average Spread While instrument is open)  = 0.0091202346</fld>

<fld id="D969"> (No of Spread update while instrument is open)  = 2046</fld>

<fld id="D970"> (Total Relative spread while instrument is op)  = 0.6981758613</fld>

<fld id="D971"> (Average spread while instrument is open)  = 0.0003412394</fld>

<fld id="D974"> (Date of last time instrument traded)  = 22-12-2015</fld>

<fld id="D1800"> (Original Trade Identification Code)  = 1-1740094</fld>

<fld id="D1852"> (Original message type)  = Z</fld>

<fld id="D1892"> (Source of BATS enhanced data (C=Composite, )  = CCC</fld>

<fld id="D1895"> (Realtime PE Ratio)  = 14.409569</fld>

<fld id="D1958"> (Realtime Forward PE Ratio)  = 11.574702</fld>

<fld id="D1961"> (Return Year to date)  = -0.37749</fld>

<fld id="D1968"> (Realtime Market Capitalization)  = 136495772834.12999</fld>

<fld id="D1978"> (Adjusted Close Price)  = 26.635</fld>

<fld id="D1979"> (Calculated VWAP)  = 28.5665892582</fld>

<fld id="D2124"> (MS Performance ID)  = 0P0000019Y</fld>

<fld id="D2125"> (MS EarningYield)  = 6.9399999528</fld>

<fld id="D2126"> (MS PERatio)  = 14.409222</fld>

<fld id="D2127"> (MS PBRatio)  = 2.275831</fld>

<fld id="D2128"> (MS DividendYield)  = 3.05</fld>

<fld id="D2129"> (MS ForwardDividendYield)  = 3.12</fld>

<fld id="D2130"> (MS ForwardEarningYield)  = 8.22</fld>

<fld id="D2131"> (MS Total Return 1 day)  = 0.95739</fld>

<fld id="D2132"> (MS MarketCap)  = 136495772834

</fld>



*/

}
