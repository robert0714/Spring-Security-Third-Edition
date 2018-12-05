package com.packtpub.springsecurity.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2EnabledEventsController {
	@Autowired
	private OAuth2RestOperations template;

	@Value("${base.url:http://localhost:8888}")
	private String baseUrl;

	@Value("${oauth.url:http://localhost:8080}")
	private String baseOauthUrl;

	@GetMapping("/events/my")
	public String eventsMy() {

		@SuppressWarnings("unchecked")
		String result = template.getForObject(baseOauthUrl + "/events/my", String.class);
		return result;
	}
}
