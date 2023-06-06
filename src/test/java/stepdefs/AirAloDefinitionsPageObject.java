package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import page_objects.LandingPage;
import page_objects.SearchResults;

public class AirAloDefinitionsPageObject {
    LandingPage landingPage;
    SearchResults searchResults;

    @Given("^I am on the Airalo homepage object$")
    public void i_can_open_site() throws Exception{
        landingPage = new LandingPage();
        Assert.assertTrue(landingPage.isLandingPageOpened());
    }

    @When("^I search Airalo for \"Canada\" option$")
    public void search_for_canada_esims() throws Exception{
        searchResults = landingPage.searchDataPacks("Canada");

    }

    @Then("^I should see \"Canada\" item listed as a \"Local\" option$")
    public void i_can_see_canada_listed_as_local_option() throws Exception{
        searchResults.assertLocalOption("Canada");
    }

    @And("^I quit the browser$")
    public void close_browser() throws Exception{
        landingPage.quit();
    }

}
