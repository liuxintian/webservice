package com.omt.agency.dao;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;


/**
 * Desc: DaoRepImpl 
 * Author: TonyLiu
 */
@Repository
public class FileStorageDaoImpl implements FileStorageDao {
	
	@Autowired
	 GridFsTemplate gridFsTemplate;

	 @SuppressWarnings("rawtypes")
	 @Override
	 public List findAll() {
	  return gridFsTemplate.find(null);
	 }

	@Override
	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
		// TODO Auto-generated method stub
		  return this.gridFsTemplate.store(inputStream, fileName, contentType, metaData).getId() .toString();
	}

	@Override
	public GridFSDBFile retrive(String fileName) {
		// TODO Auto-generated method stub
		return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
	}

	@Override
	public GridFSDBFile getById(String id) {
		// TODO Auto-generated method stub
		return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
	}

	@Override
	public GridFSDBFile getByFilename(String filename) {
		// TODO Auto-generated method stub
		return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(filename)));
	}

	 public GridFsTemplate getGridFsTemplate() {
		return gridFsTemplate;
	}

	public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
	}

}
