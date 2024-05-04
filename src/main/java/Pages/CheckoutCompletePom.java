package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePom extends BasePage{

    @FindBy(xpath = "//*[@data-test='complete-header']")
    private WebElement orderConfirmation;


    @FindBy(xpath = "//*[@data-test='back-to-products']")
    private WebElement backHome;

    public CheckoutCompletePom(WebDriver driver){
        super(driver);
    }

    public String checkOrderConfirmation(){
        return orderConfirmation.getText();
    }

    public void backToHomePage(){
        waitForElementToBeClickable(backHome);
        backHome.click();
    }

}
