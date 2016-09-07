package com.omt.websocket.dao;

import java.util.List;

import com.omt.websocket.entity.HistoryPriceVO;

/**
 * Desc: DAO Interface 
 * Author: TonyLiu
 */
 
 public interface HistoryDaoAbsRep {

	public void insert(HistoryPriceVO entity);

	public HistoryPriceVO findOne(String id);

	public HistoryPriceVO findRightOne(HistoryPriceVO entity);
	
	public void deleteOne(String id);

	public List<HistoryPriceVO> findAll();
	
	public void update(HistoryPriceVO entity);
	
	public HistoryPriceVO findMarketOne(HistoryPriceVO entity);


}
