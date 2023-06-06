package page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends Page {

    @FindBy(xpath = ".//input[contains(@placeholder, 'Search data packs for 200+ countries and regions')]")
    WebElement searchField;

    public LandingPage(){
        openUrl("https://www.airalo.com/");
    }

    public boolean isLandingPageOpened(){
        return searchField.isDisplayed();
    }

    public SearchResults searchDataPacks(String searchStr){
        searchField.sendKeys(searchStr);
        SearchResults searchResults = new SearchResults();
        searchResults.waitForSearchResults();
        return searchResults;
    }
}
