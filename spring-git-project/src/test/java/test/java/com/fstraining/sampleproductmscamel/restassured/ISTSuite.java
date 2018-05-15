package test.java.com.fstraining.sampleproductmscamel.restassured;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ProductServicemSISTTest.class })
public class ISTSuite {

	@BeforeClass
	public static void setUp() {
		
	}
	
	@AfterClass
	public static void tearDown() {
	}


}

