package cucumber.framework;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin = {"pretty"},features = {"src\\test\\Features"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
