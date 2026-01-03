package com.app.its.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class MetricServiceFeignClientConfig {

	@Autowired
	OAuth2AuthorizedClientManager auth2AuthorizedClientManager;

	@Bean
	RequestInterceptor interceptor() {
		RequestInterceptor requestInterceptor = new RequestInterceptor() {

			@Override
			public void apply(RequestTemplate template) {
				OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
						.principal("service-user").build();

				OAuth2AuthorizedClient client = auth2AuthorizedClientManager.authorize(request);
				if (client != null && client.getAccessToken() != null) {
					String accessToken = client.getAccessToken().getTokenValue();
					template.header("Authorization", "Bearer " + accessToken);
				}

			}
		};
		return requestInterceptor;
	}

}
