package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static DriverFactory driverFactory;

    protected WebDriver driver;
    FluentWait wait;

    private DriverFactory(){
        driver = getDriver();
        wait = getWait();
    }

    public static DriverFactory instance(){
        if(driverFactory == null){
            driverFactory = new DriverFactory();
        }
        return driverFactory;
    }

    public WebDriver driver(){
        return this.driver;
    }

    public FluentWait fluentWait(){
        return this.wait;
    }

    private WebDriver getDriver(){
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

    private FluentWait getWait(){
        if(wait == null) {
            wait = new FluentWait(getDriver());
            wait.withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        }
        return wait;
    }
}
