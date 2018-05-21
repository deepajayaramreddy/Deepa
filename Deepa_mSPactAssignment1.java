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

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class mSPactAssignment {
	private static Logger log = LoggerFactory.getLogger(mSPactAssignment.class); 
	@Rule 
	public PactProviderRule rule = new PactProviderRule("mSPactAssignment", PactSpecVersion.V3, this); 
 
	@Pact(provider="mSPactAssignment",consumer = "getmSPactAssignment") 
 
	public PactFragment mSPactAssignmentFragment(PactDslWithProvider builder) { 
		log.info("ENTER::public PactFragment mSPactAssignmentFragment(PactDslWithProvider builder) "); 
		Map<String, String> headers = new HashMap<>(); 
		headers.put("Content-Type", "application/json"); 

		log.info("EXIT::public PactFragment mSPactAssignmentFragment(PactDslWithProvider builder) "); 
		System.out.println("==IN CLASS-==="+builder);
		return builder 
				.uponReceiving("Date,Status,Amount values") 
				.path("/restservices/sampleAssignmentPath") 
				.method("POST") 
				.headers(headers) 
			    .body(new PactDslJsonBody()
			    		
			    		.array("Sample_Json_response1")
			    		
			    		.object()
			    		.stringType("clearnacedate","07/22/2015") 
						.stringType("status", "C") 
					    .stringType("amount", "15.0") 
					    .closeObject()
					    
					    .object()
						.stringType("clearnacedate", "07/08/2015") 
						.stringType("status", "D") 
						.stringType("amount", "17.0")  
						.closeObject()
						
						.object()
						.stringType("clearnacedate", "07/30/2018") 
						.stringType("status", "C") 
						.stringType("amount", "18.0")  
						.closeObject()
						
						.closeArray()
						)
			    .willRespondWith()
				.status(200)
				.headers(headers)
				.toFragment();
	}
		@Test 
		@PactVerification(value = "mSPactAssignment" , fragment = "mSPactAssignmentFragment") 
		
		public void assignment() throws IOException{ 
			log.trace("in consumerClient");
			ConsumerService consumerClient = new ConsumerService(rule.getConfig().url());
			log.info("In Response - consumerClient");
			int response = consumerClient.postBody("/restservices/sampleAssignmentPath", 
"{ \r\n" + 
	"\"Sample_Json_response1\": [ \r\n" +
							"{ \r\n" + 		
																	"  \"clearnacedate\": \"07/22/2015\", \r\n"  + 
																	"   \"status\": \"C\", \r\n" + 
																	"    \"amount\": \"15.0\", \r\n " +
					"  }, \r\n" + 

					"{ \r\n" + 
																		"  \"clearnacedate\": \"Add\", \r\n"  + 
																		"   \"status\": \"D\", \r\n" + 
																		"    \"amount\": \"17.0\", \r\n " +
					"  }, \r\n" + 
					"{ \r\n" + 
																		"  \"clearnacedate\": \"Add\", \r\n"  + 
																		"   \"status\": \"C\", \r\n" + 
																		"    \"amount\": \"18.0\", \r\n " +
					"  } \r\n" + 
					"  ] \r\n" + 

"}",
			ContentType.APPLICATION_JSON); 
			log.info("Printing PactTest Response");
			log.info("status"+" "+response); 
			assertEquals(response, 200 ); 
		}

}
