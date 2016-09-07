package com.omt.asxdata.dao;

import java.util.List;

import com.omt.asxdata.entity.AsxDataVO;

/**
 * Desc: DAO Interface 
 * Author: TonyLiu
 */
 
 public interface AsxDaoAbsRep {

	public void insert(AsxDataVO entity);

	public AsxDataVO findOne(String id);

	public void deleteOne(String id);

	public void updateDelete(String id);

	public List<AsxDataVO> findAll();

	public List<AsxDataVO> findByType(int type);

	public List<AsxDataVO> findByName(String name);

}
