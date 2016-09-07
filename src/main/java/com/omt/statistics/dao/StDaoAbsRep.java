package com.omt.statistics.dao;

import java.util.List;

import com.omt.statistics.entity.StPageShowVO;


/**
 * Desc: DAO Interface 
 * Author: TonyLiu
 */
 
 public interface StDaoAbsRep {

	public void insert(StPageShowVO entity);

	public StPageShowVO findOne(String id);

	public List<StPageShowVO> findAll();

	public List<StPageShowVO> findByName(String name);

}
