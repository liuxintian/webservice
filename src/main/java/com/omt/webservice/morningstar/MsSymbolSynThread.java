package com.omt.webservice.morningstar;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.omt.config.StaticConfig;
import com.omt.webservice.StaticMongoTemplate;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.morningstar.entity.Instrument;
import com.omt.websocket.WebSocketClient;

/**
 * This timer task runs everyday 7 am to check if there is changing on MS FTP server
 * @author tonyliu
 *
 */
public class MsSymbolSynThread extends TimerTask{

	private Timer timer;
	private static Logger omtlogger = Logger.getLogger(MSRequestThread.class);
	
    public MsSymbolSynThread() {
    	timer = new Timer("MsSymbolSynThread", true);
    }
    
    public void start()
    {
    	omtlogger.info("Starting MsSymbolSynThread...");
		if(timer == null){
			timer = new Timer("MsSymbolSynThread",true);
		}
		//0. run everyday
        timer.schedule(this, UtilLibs.GetNextDay20PM(), 24*60*60*1000);
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(StaticConfig.MS_SYMBOL_SYNC_FLAG == 1) {
			updateSymbols();
		}
	}
	
	/**
	 * Do real update symbol
	 */
	public static boolean updateSymbols(){
		//1. check if there is something changed [file date time, file line count]
		if(downloadMSfile()){
			//2. Reload into database
			ReLoadInstrumentForMS(getLocalFileName());
			//3. Reload into memory
			ReLoadInstrumentMemory();
			return true;
		}else{
			return false;
		}
	}
	
    public void stop()
    {
    	omtlogger.info("Stopping MsSymbolSynThread...");
    	if(timer != null) {
    		timer.cancel();
    		timer = null;
    	}
    }
    
	private static String getLocalFileName(){
		StringBuffer st = new StringBuffer();
		st.append(StaticConfig.filesBasePath+"/"+StaticConfig.MS_LOCAL_FILE);
		return st.toString();
	}
	
    private static boolean downloadMSfile(){
    	
    	boolean ret = false;
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(StaticConfig.MS_FTPSERVER_IP, StaticConfig.MS_FTPSERVER_PORT);
            ftpClient.login(StaticConfig.MS_FTPSERVER_USERNAME, StaticConfig.MS_FTPSERVER_PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            File downloadFile1 = new File(getLocalFileName());
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            ret = ftpClient.retrieveFile(StaticConfig.MS_REMOTE_FILE, outputStream1);
            outputStream1.close();
 
            if (ret) {
            	omtlogger.info("SymbolFile "+getLocalFileName()+" has been downloaded successfully.");
            }else{
            	omtlogger.info("SymbolFile "+getLocalFileName()+" has been download false.");
            }
            
            /* APPROACH #2: using InputStream retrieveFileStream(String)
            String remoteFile2 = "/test/song.mp3";
            File downloadFile2 = new File("D:/Downloads/song.mp3");
            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
 
            success = ftpClient.completePendingCommand();
            if (success) {
            	omtlogger.info("File #2 has been downloaded successfully.");
            }
            outputStream2.close();
            inputStream.close();
            */
 
        } catch (IOException ex) {
        	ret = false;
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }
    
	/**
	 * Reload instruments as successfully download new symbol file
	 * @param fullpath
	 */
    private static void ReLoadInstrumentForMS(String filename){
		BufferedReader br = null;
		String line = "";
		String splitBy = "[|]";
		
		try {
			br = new BufferedReader(new FileReader(filename));
			Instrument instrument;
			StaticMongoTemplate.getStaticMongoTemplate().dropCollection(Instrument.class);
			
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
	
	/**
	 * Reload instruments into memory
	 * @return
	 */
	private static void ReLoadInstrumentMemory(){
		Query query = new Query();
		if(StaticMongoTemplate.getStaticMongoTemplate().count(query, Instrument.class) > 0){
			try{
				DBCursor cursor = StaticMongoTemplate.getStaticMongoTemplate().getCollection("instrument").find(); 
				if(cursor != null && cursor.hasNext()){
					MsUtility.SYMBOL_INSTRUMENT_MAP.clear();
					Instrument instrument = new Instrument();
					while(cursor.hasNext()){
						DBObject obj = cursor.next();
						instrument = new Instrument();
						instrument.setExchange(obj.get("exchange").toString());
						instrument.setMarket(obj.get("market").toString());
						instrument.setSectype(obj.get("sectype").toString());
						instrument.setSymbol(obj.get("symbol").toString());
						MsUtility.SYMBOL_INSTRUMENT_MAP.put(instrument.getSymbol()+"|"+instrument.getMarket(), instrument);
					}
				} 
				cursor.close();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
