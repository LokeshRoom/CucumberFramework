package cucumber.framework;

import Operations.Operation;
import Operations.StartTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ReusableStepDefs {
    Operation operation= StartTest.getOperation();
    @Given("I open {string} page")
    public void iOpenPage(String objectUrl) {
        operation.goToUrl(objectUrl);
    }
}
