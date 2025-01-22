package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		   features= {"src/test/java/FeatureFiles/personal.feature"},
		   glue= {"StepApi"},
		   dryRun=false,
		   monochrome=true,
		   tags="@PersonalInfoApi",
		   plugin={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		   
		
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
