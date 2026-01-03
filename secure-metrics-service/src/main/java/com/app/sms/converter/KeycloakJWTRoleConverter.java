package com.app.sms.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakJWTRoleConverter implements  Converter<Jwt, Collection<GrantedAuthority>>{

	@Override
	public  Collection<GrantedAuthority> convert(Jwt jwt) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		Map<String,Object> realmAccess= (Map<String, Object>) jwt.getClaims().get("realm_access");
		if(!realmAccess.isEmpty())
		{
			List<String> roles = (List<String>) realmAccess.get("roles");
			for(String role : roles)
			{
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
			}
		}
		return authorities;
	}
	

}
