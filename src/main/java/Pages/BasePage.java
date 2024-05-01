package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToHaveText(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }
}
