package com.omt.cms.mobile.security;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

@Component
public class MobileUserTokenStore extends JdbcTokenStore {

	public MobileUserTokenStore(DataSource dataSource) {
		super(dataSource);
	}
	
	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken accessToken = super.getAccessToken(authentication);
		if(accessToken!=null && authentication!=null && authentication.getUserAuthentication()!=null){
			Object userBO = authentication.getUserAuthentication().getDetails();
			if(userBO!=null){
		        final Map<String, Object> userInfo = new HashMap<>();
		        userInfo.put("userInfo", userBO);
		        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userInfo);
			}
		}
		return accessToken;
	}

}
