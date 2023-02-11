package com.demo.login;

import java.util.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.demo.service.LoginService;


@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	AuthenticationManager authMgr;

	@Autowired
	private UserDetailsService usrSvc;
	
	@Autowired
	private LoginService loginService;
        
       
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	 converter.setSigningKey("JWTKey@123");
	return converter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean("clientPasswordEncoder")
	PasswordEncoder clientPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer cfg) throws Exception {
		
		// Enable /oauth/token_key URL used by resource server to validate JWT tokens
	    cfg.tokenKeyAccess("permitAll");
		
		// This will enable /oauth/check_token access
		cfg.checkTokenAccess("permitAll");

		// BCryptPasswordEncoder() is used for oauth_client_details.user_secret
		cfg.passwordEncoder(clientPasswordEncoder());
		
		
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		 
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(loginService), jwtAccessTokenConverter()));
	
	
	        endpoints.authenticationManager(authMgr)
	                .accessTokenConverter(jwtAccessTokenConverter())
	                .tokenStore(tokenStore())
	                .tokenEnhancer(tokenEnhancerChain)
			.userDetailsService(usrSvc);
                           	
	}
	  	
}