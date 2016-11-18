package com.omt.cms.file.op;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Shiva Kalgudi
 *
 */

@Component
public interface FileOperations {


	public FileUrl store(MultipartFile file, FileUrl finfo) throws IOException;
	
	public boolean delete(FileUrl finfo);

}
