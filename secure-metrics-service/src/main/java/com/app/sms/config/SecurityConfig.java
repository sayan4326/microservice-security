package com.app.sms.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.app.sms.converter.KeyCloakOpaqueTokenRoleConverter;
import com.app.sms.converter.KeycloakJWTRoleConverter;

@Configuration
public class SecurityConfig {
	@Value("${server.port}")
	String port;

	@Value("${client.keycloak.introspectionURI}")
	String introspectionURI;

	@Value("${client.keycloak.clientId}")
	String introspectionClientId;

	@Value("${client.keycloak.clientSecret}")
	String introspectionClientSecret;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJWTRoleConverter());
		return httpSecurity.csrf(x -> x.disable()).authorizeHttpRequests(x -> x.anyRequest().authenticated())
				// .oauth2ResourceServer(x -> x.jwt(y ->
				// y.jwtAuthenticationConverter(authenticationConverter)))
				.oauth2ResourceServer(
						x -> x.opaqueToken(y -> y.authenticationConverter(new KeyCloakOpaqueTokenRoleConverter())
								.introspectionUri(introspectionURI)
								.introspectionClientCredentials(introspectionClientId, introspectionClientSecret)))
				.build();
	}

	@Bean
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:" + port));
		configuration.addAllowedMethod(HttpMethod.GET);
		configuration.addAllowedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;

	}
}
