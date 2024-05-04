package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStep1Pom extends BasePage {

    @FindBy(id="first-name")
    private WebElement firstName;

    @FindBy(id="last-name")
    private WebElement lastName;

    @FindBy(id="postal-code")
    private WebElement postalCode;

    @FindBy(id = "cancel")
    private WebElement cancelShopping;

    @FindBy(id = "continue")
    private WebElement continueShopping;

    public CheckoutStep1Pom(WebDriver driver){
        super(driver);
    }

    public CheckoutStep1Pom setFirstName(String firstName){
        waitForElementToBeVisible(this.firstName);
        this.firstName.sendKeys(firstName);
        return this;
    }

    public CheckoutStep1Pom setLastName(String lastName){
        waitForElementToBeVisible(this.lastName);
        this.lastName.sendKeys(lastName);
        return this;
    }

    public CheckoutStep1Pom setPostalCode(String postalCode){
        waitForElementToBeVisible(this.postalCode);
        this.postalCode.sendKeys(postalCode);
        return this;
    }

    public void cancelShopping(){
        waitForElementToBeClickable(cancelShopping);
        cancelShopping.click();
    }

    public void clickContinueShopping(){
        waitForElementToBeClickable(continueShopping);
        continueShopping.click();
    }

}
