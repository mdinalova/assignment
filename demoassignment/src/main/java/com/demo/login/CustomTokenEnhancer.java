package com.demo.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.demo.model.GroupMembers;
import com.demo.service.LoginService;



public class CustomTokenEnhancer extends JwtAccessTokenConverter{
	
	@Autowired
	private LoginService loginService;
			
	@Autowired
	public CustomTokenEnhancer(LoginService loginService) {
		this.loginService = loginService;
	}


	@Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        
    	String username = authentication.getName();
    	Optional<GroupMembers> user = loginService.getByusername(username);
        	
    	Map<String, Object> additionalInfo = new HashMap<>();
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}

