package com.omt.websocket.dao;

import java.util.List;

import com.omt.websocket.entity.NotifyMessage;

/**
 * Desc: DAO Interface 
 * Author: TonyLiu
 */
 
 public interface SocketDaoAbsRep {

	public void insert(NotifyMessage entity);

	public NotifyMessage findOne(String id);

	public void deleteOne(String id);

	public List<NotifyMessage> findAll();
	
	public void update(NotifyMessage entity);

}
