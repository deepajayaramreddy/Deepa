package test.java.com.fstraining.sampleproductmscamel.pact;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

//import com.fstraining.sampleproductmscamel.Application;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactUrl;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class)
@Provider("sampleproductmscamel")
//@PactBroker (host="http://zlp25163.vci.att.com", port = "30120")
@VerificationReports({ "console", "markdown" })
@PactUrl(urls = { "file:///C://Users//sampleordermscamel//target//pacts//sampleordermscamel-sampleproductmscamel.json" })

public class ProductServicePact {
	
	private static ConfigurableApplicationContext application;
	@TestTarget
	public final Target target = new HttpTarget(8080);
	@BeforeClass
	public static void startSpring() {
		//System.out.println("........................Starting application...........");
		//application = SpringApplication.run(Application.class);
	}
	@State("FIND PRODUCT FOR CONSUMER2")
	public void toDefaultState() {
		System.out.println("Now service in default state");
	}
	@State("extra")
	public void toExtraState() {
		System.out.println("Now service in extra state");
	}
	@AfterClass
	public static void kill() {
		//application.stop();
	}
}
