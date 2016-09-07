package com.omt.webservice.dao;

import java.util.List;

import com.omt.webservice.entity.UserMessage;

/**
 * Desc: DAO Interface 
 * Author: TonyLiu
 */
 
 public interface DaoAbsRep {

	public void insert(UserMessage entity);

	public UserMessage findOne(String id);

	public void deleteOne(String id);

	public void updateStatus(String id, int status);

	public List<UserMessage> findAll();

	public List<UserMessage> findAllNormal();

	public List<UserMessage> findByName(String name);

}
