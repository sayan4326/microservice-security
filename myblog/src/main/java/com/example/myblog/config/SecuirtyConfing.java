package com.example.myblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuirtyConfing {

	@Value("${facebook.clientId}")
	private String facebookClientId;
	@Value("${facebook.clientSecret}")
	private String facebookClientSecret;

	@Value("${google.clientId}")
	private String googleClientId;
	@Value("${google.clientSecret}")
	private String googleClientSecret;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		return httpSecurity.authorizeHttpRequests(x -> x.anyRequest().authenticated())
				.oauth2Login(Customizer.withDefaults()).build();
	}

	@Bean
	ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration facebookClientRegistration = facebookClientRegistration();
		ClientRegistration googleClientRegistration = googleClientRegistration();
		return new InMemoryClientRegistrationRepository(facebookClientRegistration, googleClientRegistration);
	}

	ClientRegistration facebookClientRegistration() {
		return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId(facebookClientId)
				.clientSecret(facebookClientSecret).build();
	}

	/*
	 * it is important you check the default scopes required for the
	 * CommonOuath2Provider an dgrant them
	 */ ClientRegistration googleClientRegistration() {
		return CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId(googleClientId)
				.clientSecret(googleClientSecret).build();
	}

}
