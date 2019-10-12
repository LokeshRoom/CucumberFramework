package cucumber.framework;

import Operations.Operation;
import Operations.StartTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;

public class GooglePage {

    Operation operation= StartTest.getOperation();

    @When("I search for {string} in Google homepage")
    public void iSearchFor(String searchText) throws Exception {
        operation.enterText("txt_SearchBox","Google_HomePage",searchText);
        operation.enterText("txt_SearchBox","Google_HomePage", Keys.ENTER);

    }

    @Then("I should get results with {string}")
    public void iShouldGetResultsWith(String searchText) throws Exception {
        operation.assertTextExists(searchText);
    }
}
