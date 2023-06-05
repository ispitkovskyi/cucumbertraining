package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AirAloDefinitions {
    WebDriver driver;
    FluentWait wait;

    public AirAloDefinitions(){
        getDriver();
    }

    @Before
    @cucumber.api.java.Before(order = 0)
    public WebDriver getDriver(){
        if(driver == null || ((RemoteWebDriver) driver).getSessionId()==null) {
            //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            Map<String, Object> prefs = new HashMap();
            prefs.put("download.prompt_for_download", false);
            prefs.put("behavior", "allow");
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public FluentWait getWait(){
        if(wait == null) {
            wait = new FluentWait(getDriver());
            wait.withTimeout(Duration.ofSeconds(60));
            wait.pollingEvery(Duration.ofMillis(200));
            wait.ignoring(NoSuchElementException.class);
        }
        return wait;
    }

    @Given("^I am on the Airalo homepage$")
    public void i_can_open_site() throws Exception{
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Launch the Online Store Website
        getDriver().get("https://www.airalo.com/");

        Assert.assertTrue(getDriver().findElement(By.xpath(".//input[contains(@placeholder, 'Search data packs for 200+ countries and regions')]")).isDisplayed());
    }

    @When("^I search Airalo for \"Canada\"$")
    public void search_for_canada_esims() throws Exception{
        //getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().findElement(By.xpath(".//input[contains(@placeholder, 'Search data packs for 200+ countries and regions')]")).sendKeys("Canada");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'countires-list')]")));

    }

    @Then("^I should see \"Canada\" listed as a \"Local\" option$")
    public void i_can_see_canada_listed_as_local_option() throws Exception{
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'countires-list')]/div[descendant-or-self::p[text()='Local']]")));
        WebElement local = getDriver().findElement(By.xpath("//ul[contains(@class, 'countires-list')]/div[descendant-or-self::p[text()='Local']]"));
        WebElement canada = local.findElement(By.xpath(".//following-sibling::li[descendant-or-self::span[text()='Canada']]"));
        Assert.assertTrue(canada.isDisplayed());
    }

    @And("^I close the browser$")
    public void close_browser() throws Exception{
        getDriver().quit();
    }

}
