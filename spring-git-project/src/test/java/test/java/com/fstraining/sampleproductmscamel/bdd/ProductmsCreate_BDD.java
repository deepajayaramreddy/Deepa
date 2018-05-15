package test.java.com.fstraining.sampleproductmscamel.bdd;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

import com.eviware.soapui.model.support.PropertiesMap;

import cucumber.api.java.en.*;

public class ProductmsCreate_BDD {
	
	WsdlProject project = null;
	TestSuite testSuite = null;

	@Given("^I have a createProduct service$")
	public void i_have_a_createProduct_service() throws Throwable {
		project = new WsdlProject("src/test/resources/IST/IST-ProductMS-BDD-soapui-project.xml");
		String hostandport = System.getProperty("hostandport");
		System.out.println("In Post class==============="+hostandport);
		project.setPropertyValue("ServiceEndPoint", hostandport);
		
		testSuite = project.getTestSuiteByName("TestSuite-CreateProduct");
	}

	@When("^I call createProduct service for a product with barcode missing with (\\d+),\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",(\\d+)$")
	public void i_call_createProduct_service_for_a_product_with_barcode_missing_with(int arg1, String arg2, String arg3, String arg4, String arg5, int arg6) throws Throwable {
		TestCase testcase =  testSuite.getTestCaseByName("TestCase - MissingBarCode");
		testcase.setPropertyValue("id", String.valueOf(arg1));
		testcase.setPropertyValue("productName", arg2);
		testcase.setPropertyValue("description", arg3);
		testcase.setPropertyValue("mfgdate", arg4);
		testcase.setPropertyValue("sku", arg5);
		testcase.setPropertyValue("amount", String.valueOf(arg6));
		
		TestRunner runner = testcase.run(new PropertiesMap(), false);
		System.out.println(runner.getStatus());
	}

	@Then("^I get  a boolean status as false$")
	public void i_get_a_boolean_status_as_false() throws Throwable {
	    
	}

	@When("^I call createProduct service for a product  missing data in warehouse with (\\d+),\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",(\\d+)$")
	public void i_call_createProduct_service_for_a_product_missing_data_in_warehouse_with(int arg1, String arg2, String arg3, String arg4, String arg5, int arg6) throws Throwable {
		TestCase testcase =  testSuite.getTestCaseByName("TestCase -MissingDataInWareHouse");
		testcase.setPropertyValue("id", String.valueOf(arg1));
		testcase.setPropertyValue("productName", arg2);
		testcase.setPropertyValue("description", arg3);
		testcase.setPropertyValue("mfgdate", arg4);
		testcase.setPropertyValue("sku", arg5);
		testcase.setPropertyValue("amount", String.valueOf(arg6));
		
		TestRunner runner = testcase.run(new PropertiesMap(), false);
		System.out.println(runner.getStatus());
	}

	@When("^I call createProduct service with (\\d+),\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",(\\d+)$")
	public void i_call_createProduct_service_with(int arg1, String arg2, String arg3, String arg4, String arg5, int arg6) throws Throwable {
		TestCase testcase =  testSuite.getTestCaseByName("TestCase-ValidProduct");
		testcase.setPropertyValue("id", String.valueOf(arg1));
		testcase.setPropertyValue("productName", arg2);
		testcase.setPropertyValue("description", arg3);
		testcase.setPropertyValue("mfgdate", arg4);
		testcase.setPropertyValue("sku", arg5);
		testcase.setPropertyValue("amount", String.valueOf(arg6));
		
		TestRunner runner = testcase.run(new PropertiesMap(), false);
		System.out.println(runner.getStatus());
	}

	@Then("^I get a boolean status as true$")
	public void i_get_a_boolean_status_as_true() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}
	
	
}
