package com.app.its.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

@Configuration
public class Oauth2CLientConfig {

	@Value("${client.keycloak.clientId}")
	private String keyCloakClientId;

	@Value("${client.keycloak.clientSecret}")
	private String keyCloakSecret;

	@Value("${client.keycloak.issuerUri}")
	private String keyCloakIssuerURI;
	
	@Value("${client.keycloak.tokenUri}")
	private String keyCloakTokenURI;

	@Bean
	ClientRegistrationRepository KeycloakClientRegistrationRepository() {
		ClientRegistration keycloakClientRegistration = ClientRegistration.withRegistrationId("keycloak")
				.clientId(keyCloakClientId).clientSecret(keyCloakSecret)
				.authorizationGrantType(
						org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope("openid", "email", "roles").issuerUri(keyCloakIssuerURI).tokenUri(keyCloakTokenURI).build();

		return new InMemoryClientRegistrationRepository(keycloakClientRegistration);
	}
	
	@Bean
	OAuth2AuthorizedClientManager auth2AuthorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientService service) {
		
		OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials().build();
		AuthorizedClientServiceOAuth2AuthorizedClientManager  auth2AuthorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager (
				clientRegistrationRepository, service);
		auth2AuthorizedClientManager.setAuthorizedClientProvider(oAuth2AuthorizedClientProvider);
		return auth2AuthorizedClientManager;

	}
}
