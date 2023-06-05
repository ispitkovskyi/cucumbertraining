package page_objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ispitkovskyi on 6/19/2016.
 */
public abstract class PageAbstract {
    protected WebDriver driver;

    protected PageAbstract(){
        //driver = DriverFactory.getInstance().getDriver();
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver(){
        if(driver == null || ((RemoteWebDriver) driver).getSessionId()==null) {
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

    protected void scrollToElement(WebElement element) throws InterruptedException {
        scrollToElement(element, null);
    }
    protected void scrollToElement(WebElement element, Integer pause) throws InterruptedException {
        if(pause == null)
            pause = 500;

        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.currentThread().sleep(pause);
    }

}
