package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver wd;
    private WebDriverWait wait;
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id ="password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement submitBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorText;

    public LoginPage(WebDriver wd){
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }

    public String getUrl(){
        return wd.getCurrentUrl();
    }

    public LoginPage setUserName(String username){
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }
    public LoginPage setPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public String loginError() throws InterruptedException {
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        return errorText.getText();
    }
    public void clickSubmit(){
        submitBtn.click();
    }
}
