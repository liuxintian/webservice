package com.omt.webservice.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

public class Encryptor {

	private static Encryptor encryptor = null;
	private static StrongPasswordEncryptor passwordEncryptor = null;
	private static BasicTextEncryptor textEncryptor = null;	
	private static final String TextEncryptorPassword = "OMt$CMs$pLATfORM";	//This is a random string
	
	private static final Logger logger = LoggerFactory.getLogger(Encryptor.class.getName());
	
	private Encryptor() { }

	static {
		encryptor = new Encryptor();
		passwordEncryptor = new StrongPasswordEncryptor();
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(TextEncryptorPassword);
	}
	
	public static Encryptor getInstance() {
		return encryptor;
	}

	public String encryptString(String text) {
	    String encryptedText=null;
		try {
		    encryptedText = URLEncoder.encode(textEncryptor.encrypt(text),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Cannot encrypt the string " + text + "  Ex->" + e.getMessage());
		}
		return encryptedText;
	}
	
	public String encryptStringForced(String text) {
		String encryptedText = null;
		do {
			encryptedText = encryptString(text);
		} while(StringUtils.contains(encryptedText, '/'));
		
		return encryptedText;
	}
		
	public String decryptString(String encryptedText) {
		if(!StringUtils.isEmpty(encryptedText)) {
			return textEncryptor.decrypt(encryptedText);
		}
		else { 
			return "";
		}
	}
	
	public String encryptPassword(String password) {
		if (password != null) {
			return passwordEncryptor.encryptPassword(password);
		}
		return null;
	}

	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}
	
	
	public String getRandomPassword(int length) {
		return getRandomString(length);
	}
	
	public String getRandomString(int length) {
		Random random = new Random(); 
		char[] symbols = new char[36];
		char[] buf;
		
		for (int idx = 0; idx < 10; ++idx)
			symbols[idx] = (char) ('0' + idx);
	    for (int idx = 10; idx < 36; ++idx)
	    	symbols[idx] = (char) ('a' + idx - 10);
	    
	    buf = new char[length];
	    
	    for (int idx = 0; idx < buf.length; ++idx) 
	    	buf[idx] = symbols[random.nextInt(symbols.length)]; 
	    
	    return new String(buf);
	}
	

	public String getRandomNumber(int length) {
		Random random = new Random(); 
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<length; i++){
			builder.append(random.nextInt(10));
		}
		return builder.toString(); 
	}

	public List<String> encryptList(List<?> dataList){
		List<String> encryptList = new ArrayList<String>();
		//Encrypt each data item from the input list and return a new encrypted list
		for(Object id : dataList){
			encryptList.add(encryptString(id.toString()));
		}
		return encryptList;
	}	

	public static void main(String args[]){
		System.out.println("----aa:"+Encryptor.getInstance().encryptString(TextEncryptorPassword));
		System.out.println("----bb:"+Encryptor.getInstance().encryptStringForced(TextEncryptorPassword));
		System.out.println("----dd:"+Encryptor.getInstance().encryptPassword("omt@mel123"));
		System.out.println("----ee:"+Encryptor.getInstance().encryptPassword("abcd"));
//		----dd:oP9UZht6Vmp+/5vAoae8JS+En0oxW3hyQIeYipEVilzQdEyhfDV/xbIZXroMS9cn
//		----ee:i8xLZfTd2oRPuxJ/B89BzY1uArBmTVBs70+vxMgSP5BWvRxIX+X9F5lL80YSEFYY
		System.out.println("----11:"+Encryptor.getInstance().encryptString("omt@mel123****************"));
		System.out.println("----22:"+textEncryptor.decrypt(textEncryptor.encrypt("omt@mel123****************")));
		System.out.println("----33:"+ (Encryptor.getInstance().getRandomPassword(10)));
		System.out.println("----44:"+textEncryptor.encrypt(Encryptor.getInstance().getRandomPassword(10)));
		System.out.println("----55:"+textEncryptor.decrypt("+s1JYVfi6Fuy5Zx4c1CdLJ8OAeqGbXIl/fbeTCCyC58hFfjAqsfMuXtTXF81ZMUD"));
		System.out.println("----66:"+textEncryptor.decrypt("NTCNheMVrZW9rAyVwuIgb7Wfw7KE9xhO2I34W2i+08Jlz0bjW/q9KZzfBxyzw9YP"));
	}
}
