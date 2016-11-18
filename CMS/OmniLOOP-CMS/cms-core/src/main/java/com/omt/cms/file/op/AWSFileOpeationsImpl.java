package com.omt.cms.file.op;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public class AWSFileOpeationsImpl implements FileOperations {

	@Override
	public FileUrl store(MultipartFile file, FileUrl finfo) throws IOException {
		// TODO Auto-generated method stub
		String fileName = System.currentTimeMillis() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
		String fullPathName = finfo.getFileFullPath() +"/"+ fileName;
    	File dir = new File(finfo.getFileFullPath());
    	if(!dir.exists())
    	{
    		dir.mkdirs();
    	}
    	
    	File temp = new File(fullPathName);
    	file.transferTo(temp);
    	
    	finfo.setFileName(fileName);

		return finfo;
	}


	@Override
	public boolean delete(FileUrl finfo) {
		// TODO Auto-generated method stub
		boolean status = false;
		if(finfo != null){
			File file = new File(finfo.getFileFullName());
			if(file.exists()){
				status = file.delete();
			}
		}
		return status;
	}
}
