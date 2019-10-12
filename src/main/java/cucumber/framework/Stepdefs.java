package cucumber.framework;

import DriverFactory.Browsers;
import Operations.Operation;
import Operations.StartTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Stepdefs {

    public StartTest startTest;

    @Then("I launch {string} browser in {string} enviroment using {string} Object Repository")
    public void iLaunchBrowserInEnviromentUsingObjectRepository(String browser, String env, String objectRepo) throws Exception {
     Browsers browsers=null;
     if (browser.equalsIgnoreCase("chrome"))
         browsers=Browsers.CHROME;
     else if (browser.equalsIgnoreCase("ie"))
         browsers=Browsers.IE;
     else if((browser.equalsIgnoreCase("firefox")))
         browsers=Browsers.FIREFOX;
     else
         throw new Exception("Provide correct browser value");
        startTest=new StartTest(browsers,env,objectRepo);
    }
}
