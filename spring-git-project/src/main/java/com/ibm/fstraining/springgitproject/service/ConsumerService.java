package com.ibm.fstraining.springgitproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
/*Used for the PACT Contract verification only*/
public class ConsumerService {

	private String url;
	private RestTemplate restTemplate;

	@Autowired
	public ConsumerService(@Value("${producer}") String url) {
		this.url = url;
		this.restTemplate = new RestTemplate();
	}

	public Object getWelcomeMsg() {
		return restTemplate.exchange(url+ "/sampleproductmscamel/service/hello?name=User",HttpMethod.GET, null, String.class).getBody();
	}

}