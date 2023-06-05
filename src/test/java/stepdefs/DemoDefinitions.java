package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DemoDefinitions {
    WebDriver driver;

    public DemoDefinitions(){
        getDriver();
    }

//    @Before
//    @cucumber.api.java.Before(order = 0)
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

    @Given("^I can open web page and dismiss alert$")
    public void i_can_open_site() throws Exception{
        System.out.println("I can open web page and dismiss alert WORKS!");

        System.out.println("I run it within my IDE WORKS!");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Launch the Online Store Website
        getDriver().get("https://demoqa.com/books");

        getDriver().findElement(By.xpath(".//input[@id='searchBox']")).click();
        //throw new PendingException();
    }

    //SIMPLE TEST
    @When("^I can login into my account$")
    public void i_can_login_into_account() throws Exception{
        System.out.println("I can login into my account WORKS!");
        getDriver().findElement(By.xpath(".//button[@id='login']")).click();
        getDriver().findElement(By.id("userName")).sendKeys("igortest_1");
        getDriver().findElement(By.id("password")).sendKeys("Test@123Test!123");
        getDriver().findElement(By.id("login")).click();
        System.out.println("Login Successfully");
        //throw new PendingException();
    }

    //PARAMETERIZED TEST or a SCENARIO OUTLINE (with table of Examples)
    @When("^I can login into my account as \"(.*)\" and \"(.*)\"$")
    public void i_can_login_into_account_parameterized(String username, String password) throws Exception{
        System.out.println("I can login into my account WORKS!");
        getDriver().findElement(By.xpath(".//button[@id='login']")).click();
        getDriver().findElement(By.id("userName")).sendKeys(username);
        getDriver().findElement(By.id("password")).sendKeys(password);
        getDriver().findElement(By.id("login")).click();
        System.out.println("Login Successfully");
        //throw new PendingException();
    }

    //TEST WITH DATA TABLES
    @When("^I can login into my account and logout many times with Data Tables$")
    public void i_can_login_into_account_data_tables(DataTable usercredentials) throws Exception{
        //Write the code to handle Data Table
        for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {
            System.out.println(String.format("Login with credentials %s/%s", data.get("Username"), data.get("Password")));
            getDriver().findElement(By.xpath(".//a[text()='My Account']")).click();
            getDriver().findElement(By.id("userName")).sendKeys(data.get("Username"));
            getDriver().findElement(By.id("password")).sendKeys(data.get("Password"));
            getDriver().findElement(By.name("login")).click();
            System.out.println("I can logout from my account successfully WORKS!");
            getDriver().findElement (By.xpath(".//a[text()='Logout']")).click();
            System.out.println("LogOut Successfully");
        }
        getDriver().quit();
        //throw new PendingException();
    }

    @When("^I can login into my account with Credentials and logout many times with Class Objects$")
    public void user_enters_testuser_and_Test(List<Credentials> usercredentials) throws Throwable {
        //Write the code to handle Credentials object
        for (Credentials credentials : usercredentials) {
            System.out.println(String.format("Login with credentials %s/%s", credentials.getUsername(), credentials.getPassword()));
            getDriver().findElement(By.xpath(".//a[text()='My Account']")).click();
            getDriver().findElement(By.id("userName")).sendKeys(credentials.getUsername());
            getDriver().findElement(By.id("password")).sendKeys(credentials.getPassword());
            getDriver().findElement(By.name("login")).click();
            System.out.println("I can logout from my account successfully WORKS!");
            getDriver().findElement (By.xpath(".//a[text()='Logout']")).click();
            System.out.println("LogOut Successfully");
        }
        getDriver().quit();
    }

    @Then("^I can logout from my account successfully$")
    public void i_can_logout_from_account() throws Exception{
        System.out.println("I can logout from my account successfully WORKS!");
        // Find the element that's ID attribute is 'account_logout' (Log Out)
        getDriver().findElement (By.xpath(".//button[text()='Log out']")).click();
        // Print a Log In message to the screen
        System.out.println("LogOut Successfully");
        // Close the driver
        getDriver().quit();

        //throw new PendingException();
    }

    @Then("^I'm happy now$")
    public void i_am_happy_now() throws Exception{
        System.out.println("I'm happy now!");
    }

}
