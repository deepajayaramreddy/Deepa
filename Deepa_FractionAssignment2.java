package com.att.edfms.customerhierarchyservice.pact;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class FractionAssignment {
	
	//	{
			
			@Rule
			public PactProviderRule rule = new PactProviderRule("FractionAssignment",
					PactSpecVersion.V3, this);

			

			@Pact(provider = "FractionAssignment", consumer = "FractionAssignment")
			public PactFragment createFragment(PactDslWithProvider builder) {
				System.out
						.println("===============================productPact===============================");

				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");

				
				return builder.uponReceiving("find product for Customer Type 1")
						.path("/test").method("GET")
						.query("a=1").willRespondWith().status(200)
						.headers(headers).body(((PactDslJsonBody) ((PactDslJsonBody) new PactDslJsonBody()
						.array("features")
			    		.object()
			    		.object("geometry")
			    		.array("coordinate")
			    		.stringValue("-7.178")
			    		.stringValue("49.2345")
			    		.closeArray())
			    		.stringType("type","Point")
			    		.closeObject()
			    		.array("properties")
			    		.stringValue("Property1")
			    		.stringValue("value1")
			    		
			    		.closeArray()
			    		.closeObject()
			    		.closeArray())
			    		
			    		.stringType("type", "Sample")
			    		
			    		
						)
	
						.toFragment();

			}

			@Test
			@PactVerification(value = "FractionAssignment", fragment = "createFragment")
			public void runTest() {
				final RestTemplate call = new RestTemplate();
				System.out
						.println("*******************************************Inside ProductPAct========================================");
				final ResponseEntity<String> responseStr1 = call.getForEntity(rule
						.getConfig().url() + "/test?a=1",
						String.class);
				
			}
			    

		
		
}
