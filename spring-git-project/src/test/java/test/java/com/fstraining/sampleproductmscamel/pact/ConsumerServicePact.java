package test.java.com.fstraining.sampleproductmscamel.pact;

import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;

import com.ibm.fstraining.springgitproject.service.ConsumerService;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class ConsumerServicePact {
	
	@Rule
	    public PactProviderRule rule = new PactProviderRule("sampleproductmscamel", PactSpecVersion.V3, this);

	    @Pact(provider="sampleproductmscamel",consumer = "sampleproductmscamel") 
	    public PactFragment createFragment(PactDslWithProvider builder) { 
	    	
	    	Map<String, String> headers = new HashMap<>(); 
	    	headers.put("Content-Type", "application/json"); 
	    	
	    	return builder
	    			.given("welcomeTest")
	    			
	    			.uponReceiving("a request to get the welcome test of a user")
	    			.path("/sampleproductmscamel/service/hello")
	    			.method("GET")
	    			.query("name=User")
	    			.willRespondWith()
	    			.headers(headers)
	    			.status(200)
	    			.body(new PactDslJsonBody()
	    			.stringType("message", "Hello User!"))
	           		.toFragment();
	    	
	    }
	    
	    @Test 
	    @PactVerification(value = "sampleproductmscamel" , fragment = "createFragment") 
	    public void runTest(){ 
	    	assertEquals(new ConsumerService(rule.getConfig().url()).getWelcomeMsg().toString(),"{\"message\":\"Hello User!\"}");
	    }
	    

}