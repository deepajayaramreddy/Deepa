package test.java.com.fstraining.sampleproductmscamel.bdd;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ProductmsGet_BDD {

	//SoapUITestCaseRunner caseRunner = new SoapUITestCaseRunner();
	WsdlProject project = null;
	TestSuite testSuite = null;
	
	@Given("^I have a getProduct service$")
	public void i_have_a_getProduct_service() throws Throwable {
		project = new WsdlProject("src/test/resources/IST/IST-ProductMS-BDD-soapui-project.xml");
		String hostandport = System.getProperty("hostandport");
		System.out.println("In Get class==============="+hostandport);
		project.setPropertyValue("ServiceEndPoint", hostandport);
		
		testSuite = project.getTestSuiteByName("TestSuite-GetProduct");
		//String projectFile = "C:\\xmls\\IST-ProductMS-soapui-project.xml";
		//caseRunner.setProjectFile(projectFile);
		//caseRunner.setTestSuite("TestSuite-GetProduct");
	}

	@When("^I call getProduct service for customerTypeone with (\\d+) and (\\d+)$")
	public void i_call_getProduct_service_for_customerTypeone_with_and(int arg1, int arg2) throws Throwable {
		
		TestCase testcase =  testSuite.getTestCaseByName("TestCase-ExistingProd- CustomerType 1");
		
		testcase.setPropertyValue("id", String.valueOf(arg1));
		testcase.setPropertyValue("customerType", String.valueOf(arg2));

		TestRunner runner = testcase.run(new PropertiesMap(), false);
		System.out.println(runner.getStatus());
		//caseRunner.setTestCase("TestCase-ExistingProd- CustomerType 1");
		//String [] projectProperties = {"id="+arg1,"customerType="+arg2};
		//caseRunner.setProjectProperties(projectProperties);
		
		//caseRunner.run();
		
	}

	@Then("^I get a response code of \"([^\"]*)\"$")
	public void i_get_a_response_code_of(String arg1) throws Throwable {
		
	}
	
   // @ContinueNextStepsFor({Exception.class})
	@Then("^I get a Product json response with date in format YYYY/MM/DD$")
	public void i_get_a_Product_json_response_with_date_in_format_YYYY_MM_DD() throws Throwable {
		//try{
		//	caseRunner.run();
		//}catch(Exception e){
		//	e.printStackTrace();
		//}
		
	}

	@When("^I call getProduct service for customerTypetwo with (\\d+) and (\\d+)$")
	public void i_call_getProduct_service_for_customerTypetwo_with_and(int arg1, int arg2) throws Throwable {
		/*caseRunner.setTestCase("TestCase-ExistingProd- CustomerType 2");
		String [] projectProperties = {"id="+arg1,"customerType="+arg2};
		caseRunner.setProjectProperties(projectProperties);
		caseRunner.run();*/
		
		TestCase testcase =  testSuite.getTestCaseByName("TestCase-ExistingProd- CustomerType 2");
		
		testcase.setPropertyValue("id", String.valueOf(arg1));
		testcase.setPropertyValue("customerType", String.valueOf(arg2));

		TestRunner runner = testcase.run(new PropertiesMap(), false);
		System.out.println(runner.getStatus());

		
	}

	@Then("^I get a Product json response with date in format MM/DD/YY$")
	public void i_get_a_Product_json_response_with_date_in_format_MM_DD_YY() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		//caseRunner.run();
	}

	
}
