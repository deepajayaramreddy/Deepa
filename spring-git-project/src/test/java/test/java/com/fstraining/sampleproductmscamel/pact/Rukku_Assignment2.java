package com.att.pricerd.sampleproductmscamel.pact;


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

public class Rukku_Assignment2 {
	
	private static Logger log = LoggerFactory.getLogger(Rukku_Assignment2.class); 
	@Rule 
	public PactProviderRule rule = new PactProviderRule("Assignment2", PactSpecVersion.V3, this); 
 
	@Pact(provider="Assignment2",consumer = "getAssignment2") 
 
	public PactFragment Assignment2Fragment(PactDslWithProvider builder) { 
		log.info("ENTER::public PactFragment Assignment2Fragment(PactDslWithProvider builder) "); 
		Map<String, String> headers = new HashMap<>(); 
		headers.put("Content-Type", "application/json"); 
		
		log.info("EXIT::public PactFragment Assignment2Fragment(PactDslWithProvider builder) "); 
		System.out.println("==IN CLASS-==="+builder);
//		String a="-7.178" , b="49.2345";
		return builder 
				.uponReceiving("Geometry values") 
				.path("/restservices/sampleAssignmentPath") 
				.method("POST") 
				.headers(headers) 
			 
				/* .body(new PactDslJsonBody()
			    		.object("Sample_Json_Response2")
			    		.array("features")
			    		.object("geometry")
			    		.array("coordinate")
			    		.asBody()
			    		.integerType(a,b)
			    		.close()
			    		.closeArray()
			    		.stringType("type","Point") 
					    .closeObject()
					    .stringType("type", "Feature")
					    .object("geometry")
					    .closeArray()
					    .stringType("type", "Sample")
						)*/
				
			/*	{ 
					"features": [ {
					"geometry": { 
					"coordinate": [ 
					  "-7.178", "49.2345", 
					  ], 
					  "type": "Point", 
					  }, 
					  "type": "Feature", 
					  }, 
					"properties": [ 
					  "Property1", "value1", 
					  ] 
					  } ], 
					  "type", "Sample", 
					  } 
					})
				*/
/*.body("{\"features\":"
		+ "{ \"geometry\":"
		+ "{\"coordinate\":"
		+ " [\"-7.178\",\"49.2345\"],"
		+ "\"type\": \"Point\",},"
		+ "\"type\": \"Feature\",},"
		+ "\"properties\": [\"Property1\",\"value1\"]"
		+ "}},"
		+ "\"type\": \"Sample\",}}")*/

.body(new PactDslJsonBody()

.object("breakpack")
.stringType("type","string")

//.asArray()
.array("enum")
//.stringType("enum")
//.closeArray()
.stringType("YES")
.stringType("No")
.stringType("ONLY")

.closeArray()
.closeObject()
.object("containerType")
.stringType("type","array")
.stringType("minItems","1")

.object("items")
.stringType("type","string")
.array("enum")
.stringType("PALLET")
.stringType("RACK")
.stringType("CARTON")
.stringType("ANY")
.closeArray()
.closeObject()
.closeObject()
)
.willRespondWith()
.status(200)
.headers(headers)
.toFragment();
}
		@Test 
		@PactVerification(value = "Assignment2" , fragment = "Assignment2Fragment") 
		
		public void assignment() throws IOException{ 
			log.trace("in consumerClient");
			ConsumerService consumerClient = new ConsumerService(rule.getConfig().url());
			log.info("In Response - consumerClient");
			int response = consumerClient.postBody("/restservices/sampleAssignmentPath", 
/*"{ \r\n" +"\"features\": { \r\n" +
	
"\"geometry\": { \r\n" +

"\"coordinate\": ["+"\"-7.178\",\"49.2345\"\r\n"  + 
					"  ], \r\n" +"\"type\": \"Point\", \r\n"  +
					"  }, \r\n" +"\"type\": \"Feature\", \r\n"  +
					"  }, \r\n" +
"\"properties\": [" +"\"Property1\", \"value1\"\r\n"  + 
			"  ] \r\n" +
			"  }}, \r\n" +"\"type\" : \"Sample\", \r\n"  +
            "  } \r\n" + 						

"}",*/
					"{ \r\n" + 
					"\"breakpack\": { \r\n" +
											 		
																					"  \"type\": \"string\", \r\n"  + 
																				
" \"enum\" :\r\n" +											
										"[ \r\n" +
																				//	"   \"enum\", \r\n" + 
																					"    \"YES\", \r\n " +
																					"    \"NO\", \r\n " +
																					"    \"ONLY\"\r\n " +
									    "  ] \r\n" +
										
									"  }, \r\n" + 
									"  \"containerType\": \r\n"  + 
									"{ \r\n" + 
																						"   \"minItems\": \"1\", \r\n" + 

																				        "  \"type\": \"array\", \r\n"  + 
																					
																					
"  \"items\": \r\n"  + 			
									"{ \r\n" + 
																						
																						"   \"type\": \"string\", \r\n" + 
 "   \"enum\": \r\n" + 												
									"[ \r\n" + 
									
									                                        
																					"    \"PALLET\", \r\n " +
																					"    \"RACK\", \r\n " +
																					"    \"CARTON\", \r\n " +
																					"    \"ANY\" \r\n " +
									
									"  ] \r\n" +
									"  } \r\n" + 
									"  } \r\n" + 

				"}",
					
								ContentType.APPLICATION_JSON); 
			log.info("Printing PactTest Response");
			log.info("status"+" "+response); 
			assertEquals(response, 200 ); 
			
		}
}
