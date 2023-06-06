package page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends Page {

    private final String searchResultsXpath = "//ul[contains(@class, 'countires-list')]";

    @FindBy(xpath = searchResultsXpath)
    WebElement searchResults;

    @FindBy(xpath = searchResultsXpath + "/div[descendant-or-self::p[text()='Local']]")
    WebElement local;

    public SearchResultsPage waitForSearchResults(){
        //Page.waitForCondition(20, 200, obj -> searchResults.isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(searchResults));
        return this;
    }

    public void assertLocalOption(String countryName){
        wait.until(ExpectedConditions.visibilityOf(local));
        WebElement country = local.findElement(By.xpath(String.format(".//following-sibling::li[descendant-or-self::span[text()='%s']]", countryName)));
        Assert.assertTrue(country.isDisplayed());
    }
}
