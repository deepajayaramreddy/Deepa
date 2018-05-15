package com.ibm.fstraining.springgitproject.service.rs;

import org.springframework.web.client.RestTemplate;


public class RestTemplateServiceImpl implements RestTemplateService {

	private RestTemplate restTemplate =  new RestTemplate();
	
	@Override
	public String getExternalDetails(String url) {
		return restTemplate.getForObject(url, String.class);
	}

}
