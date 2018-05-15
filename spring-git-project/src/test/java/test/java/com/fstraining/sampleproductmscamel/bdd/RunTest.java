package test.java.com.fstraining.sampleproductmscamel.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// Associates Cucumber-JVM with the JUnit runner
@RunWith(Cucumber.class)
//@CucumberOptions(format = { "html:target/cucumber-html-report", "json:target/cucumber-json-" + "report.json" },plugin ={"pretty"})

@CucumberOptions(
		
	    monochrome = true,
	    features = "src/test/resources/featureFiles/",
	    plugin = { "pretty","html:target/cucumber-html-report" }
	    )

public class RunTest {
}
