package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStep2Pom extends BasePage {

    @FindBy(xpath = "//*[@data-test='payment-info-value']")
    private WebElement paymentInfo;

    @FindBy(xpath = "//*[@data-test='subtotal-label']")
    private WebElement itemTotal;

    @FindBy(xpath = "//*[@data-test='tax-label']")
    private WebElement taxAmount;

    @FindBy(xpath = "//*[@data-test='total-label']")
    private WebElement totalAmount;

    @FindBy(id = "cancel")
    private WebElement cancelShopping;

    @FindBy(id = "finish")
    private WebElement finishShopping;

    public CheckoutStep2Pom(WebDriver driver){
        super(driver);
    }

    public String paymentInfo(){
        return paymentInfo.getText();
    }

    public String subTotalAmount(){
        return itemTotal.getText();
    }

    public String taxAmount(){
        return taxAmount.getText();
    }

    public String totalAmount(){
        return totalAmount.getText();
    }

    public void cancelShopping(){
        waitForElementToBeClickable(cancelShopping);
        cancelShopping.click();
    }

    public void clickFinishShopping(){
        waitForElementToBeClickable(finishShopping);
        finishShopping.click();
    }

}
