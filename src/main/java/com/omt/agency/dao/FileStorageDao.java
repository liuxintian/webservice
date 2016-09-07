package com.omt.agency.dao;

import java.io.InputStream;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * MongoDB file Operation 
 * Author: TonyLiu
 */
 
 public interface FileStorageDao {

	 public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData);
			 
	 public GridFSDBFile retrive(String fileName);
	 
	 public GridFSDBFile getById(String id);
	 
	 public GridFSDBFile getByFilename(String filename);
	 
	 @SuppressWarnings("rawtypes")
	 public List findAll();

}
