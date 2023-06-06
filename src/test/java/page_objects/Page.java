package page_objects;

import framework.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Function;

/**
 * Created by ispitkovskyi on 6/19/2016.
 */
public abstract class Page {
    protected WebDriver driver;
    FluentWait wait;
    
    protected Page(){
        driver = DriverFactory.instance().driver();
        PageFactory.initElements(driver, this);
        wait = DriverFactory.instance().fluentWait();
    }

    protected void openUrl(String url){
        driver.get(url);
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

/*    protected void waitForElementByXpath(WebElement element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getXpath(element.getClass()))));
    }

    protected String getXpath(Class<? extends WebElement> elementClass) {
        if (elementClass.isAnnotationPresent(FindBy.class)) {
            return elementClass.getAnnotation(FindBy.class).xpath();
        } else return null;
    }*/

    public void quit(){
        driver.quit();
    }

    public static boolean waitForCondition(int attempts, int delay, Function<Object, Boolean> func) {
        String defaultErrorMessage = String.format("Condition was not met; attempts: %s; delay: %s", attempts, delay);

        while (attempts > 0) {
            boolean result = false;
            try {
                result = func.apply(null);
            } catch (StaleElementReferenceException | InvalidArgumentException ignored) {
            }
            if (result) return true;

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
            }
            attempts--;
            System.out.println("Attempts left: " + attempts);
        }
        System.out.println("Error waiting for element: " + defaultErrorMessage);
        return false;
    }

}
