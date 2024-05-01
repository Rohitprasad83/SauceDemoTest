package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage extends BasePage{
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id ="password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement submitBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorText;

    public LoginPage(WebDriver wd){
        super(wd);
        PageFactory.initElements(wd, this);
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
        waitForElementToBeVisible(errorText);
        return errorText.getText();
    }
    public void clickSubmit(){
        submitBtn.click();
    }
}
