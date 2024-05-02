package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(xpath = "//*[@data-test='continue-shopping']")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//*[@data-test='checkout']")
    private WebElement checkoutBtn;

    @FindBy(xpath = "//*[@data-test='inventory-item']")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//*[@data-test='inventory-item']//button")
    private List<WebElement> cartItemRemoveButtons;

    public CartPage(WebDriver wd){
        super(wd);
    }

    public CartPage removeAllItemsFromCart(){
        for(WebElement el: cartItemRemoveButtons){
            el.click();
        }
        return this;
    }

    public CartPage checkout(){
        checkoutBtn.click();
        return this;
    }

    public CartPage backToProductsPage(){
        continueShoppingBtn.click();
        return this;
    }

}
