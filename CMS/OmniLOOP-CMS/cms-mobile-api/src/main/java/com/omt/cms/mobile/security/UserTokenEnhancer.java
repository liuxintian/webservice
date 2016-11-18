package com.omt.cms.mobile.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Component
public class UserTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth) {
		if(accessToken!=null && auth!=null && auth.getUserAuthentication()!=null){
			Object userBO = auth.getUserAuthentication().getDetails();
			if(userBO!=null){
		        final Map<String, Object> userInfo = new HashMap<>();
		        userInfo.put("userInfo", userBO);
		        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userInfo);
			}
		}
        return accessToken;
	}
	
}
