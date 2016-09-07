package com.omt.wsserver.fileupload;


import com.omt.config.StaticConfig;
import com.omt.webservice.RestUriConstant;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.morningstar.entity.Instrument;
import com.omt.websocket.WebSocketClient;
import com.omt.wsserver.fileupload.CrunchifyFileUpload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
 
@Controller
@RequestMapping(value=RestUriConstant.WEB_FILE)
public class CrunchifyFileUploadController {
	
	private static Logger omtlogger = Logger.getLogger(CrunchifyFileUploadController.class);
	private static final String UPLOAD_BASE = "/uploaded/";
	
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String crunchifyDisplayForm() {
        return "uploadfile";
    }
 
    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, String> crunchifySave(@ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm) throws IllegalStateException, IOException {
    	Map<String, String> map = new HashMap<String, String>(); 
    	String saveDirectory = StaticConfig.filesBasePath + UPLOAD_BASE;
 
        List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
 
        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
            for (MultipartFile multipartFile : crunchifyFiles) {
 
                String fileName = multipartFile.getOriginalFilename();
                omtlogger.info("Receive uploaded file:"+ fileName);
                if (!"".equalsIgnoreCase(fileName)) {
                    // Handle file content - multipartFile.getInputStream()
                    multipartFile.transferTo(new File(saveDirectory + fileName));
                    fileNames.add(fileName);

                    // new timer task for parsing and insert
                    // loadInstrumentForMS(saveDirectory + fileName);
                }
            }
        }
 
        map.put("result", "Successfully upload the file.");
        return map;
    }
    
	public static void loadInstrumentForMS(String fullpath){
		
		BufferedReader br = null;
		String line = "";
		String splitBy = "[|]";

		try {
			br = new BufferedReader(new FileReader(fullpath));
			Instrument instrument;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] ary = line.split(splitBy);
				if(ary != null && ary.length > 2){
					if((ary[2] != null && ary[2].trim().length() > 0) &&
					   (ary[1] != null && ary[1].trim().length() > 0) &&
					   (ary[0] != null && ary[0].trim().length() > 0) ){
						instrument = new Instrument();
						instrument.setMarket(WebSocketClient.DEFAULT_MARKT);
						instrument.setExchange(ary[0]);
						instrument.setSectype(ary[1]);
						instrument.setSymbol(ary[2]);
						if(ary.length > 4){
							instrument.setSymbolname(ary[5]);
						}else{
							instrument.setSymbolname(ary[2]);
						}
						
						StaticMongoTemplate.getStaticMongoTemplate().insert(instrument);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}