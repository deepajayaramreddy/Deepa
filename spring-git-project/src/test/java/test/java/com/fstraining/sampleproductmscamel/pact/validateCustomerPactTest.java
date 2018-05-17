package test.java.com.fstraining.sampleproductmscamel.pact;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.model.PactSpecVersion;

public class validateCustomerPactTest {
	@Rule
	public PactProviderRule rule = new PactProviderRule("getCustomerIdProvider", PactSpecVersion.V3, this);

	public static String getCustomerId() {
		JsonObject request = new JsonObject();

		JsonObject objj1 = new JsonObject();
		objj1.addProperty("customerIdentifier", "1111000280");
		objj1.addProperty("CustomerName", "AMERICA WEST AIRLINES");
		objj1.addProperty("customerIdentifierType", "ANCHOR");
		objj1.addProperty("customerStatus", "PENDING");
		objj1.addProperty("owningAttuid", "pk502e");
		objj1.addProperty("customerIdentifierValue", "t87t");

		request.add("CustomerSearch", objj1);
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		System.out.println("Request:-" + gson.toJson(request));
		return gson.toJson(request);

	}

	@Pact(provider = "getCustomerIdProvider", consumer = "getCustomerIdConsumer")
	public PactFragment createFragment1(PactDslWithProvider builder) {

		Map<String, String> respheaders = new HashMap<String, String>();
		respheaders.put("Content-Type", "application/json");
		respheaders.put("accept", "application/json");
		PactDslJsonBody dslJson = new PactDslJsonBody().integerType("customerIdentifier ").stringType("customerName")
				.stringType("customerIdentifierType").stringType("parentCustomerIdentifier")
				.stringType("customerIdentifierValue").stringType("customerGovernanceTeam").stringType("owningAttuid")
				.stringType("segment").asBody();
		return builder.given("Request to Server")// given refers to state
				.uponReceiving("find success template as sample")
				.path("/custmgmt/cuhi/customerhierarchy/customer/inquire").method("POST").headers(getHeaders())
				.body(validateCustomerPactTest.getCustomerId()).willRespondWith().status(200).headers(respheaders)
				.body(dslJson).toFragment();

	}

	public static Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("accept", "application/json");
		return headers;
	}

	public static HttpHeaders getRequestHeaders() {
		HttpHeaders requestheader = new HttpHeaders();
		requestheader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestheader.setContentType(MediaType.APPLICATION_JSON);
		requestheader.setAccessControlAllowCredentials(true);
		return requestheader;
	}

	@Test
	@PactVerification(value = "getCustomerIdProvider", fragment = "createFragment1")
	public void runTest1() {
		String u = rule.getConfig().url() + "/custmgmt/cuhi/customerhierarchy/customer/inquire";
		UriComponentsBuilder buil = UriComponentsBuilder.fromUriString(u);
		HttpEntity<String> requestentity = new HttpEntity<String>(validateCustomerPactTest.getCustomerId(),
				getRequestHeaders());

		final RestTemplate call = new RestTemplate();
		final ResponseEntity<String> responseString = call.exchange(buil.buildAndExpand("").toUri(), HttpMethod.POST,
				requestentity, String.class);
		assertEquals(responseString.getStatusCodeValue(), 200);
		System.out.println("TC executed 1: valid request");
		System.out.println(responseString.getStatusCodeValue() + "status code value");
		System.out.println(responseString.getBody());

	}

	
}
