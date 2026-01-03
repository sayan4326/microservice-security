package com.app.sms.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;

public class KeyCloakOpaqueTokenRoleConverter implements OpaqueTokenAuthenticationConverter {

	@Override
	public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
		Map<String, Object> realmAccess = authenticatedPrincipal.getAttribute("realm_access");
		List<String> roles = (List<String>) realmAccess.get("roles");
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles)
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return new UsernamePasswordAuthenticationToken(authenticatedPrincipal.getAttribute("preferred_username"), null, authorities);
	}

}
