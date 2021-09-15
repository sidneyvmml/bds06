package com.devsuperior.movieflix.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = userRepository.findByEmail(authentication.getName()); //pega o email
		
		//acrecentar no token
		Map<String, Object> map = new HashMap<>();
		//o que vai inserir no token		
		map.put("userId", user.getId());
		
		//inserir no token
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken; //downcast
		token.setAdditionalInformation(map);
		
		// return token; ou
		return accessToken;
	}

}
