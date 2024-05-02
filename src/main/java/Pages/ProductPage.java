package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//*[@data-test='inventory-item-name']")
    private WebElement productTitle;

    @FindBy(xpath = "//*[@data-test='inventory-item-desc']")
    private WebElement productDescription;

    @FindBy(xpath = "//*[@data-test='inventory-item-price']")
    private WebElement productPrice;

    @FindBy(xpath = "//*[@data-test='add-to-cart']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//*[@data-test='remove']")
    private WebElement removeFromCartBtn;

    @FindBy(xpath = "//*[@data-test='back-to-products']")
    private WebElement productsPageBtn;

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public String getProductTitle(){
        return productTitle.getText();
    }

    public String getProductDescription(){
        return productDescription.getText();
    }

    public String getProductPrice(){
        return productPrice.getText();
    }

    public ProductPage addProductToCart(){
        addToCartBtn.click();
        return this;
    }

    public ProductPage removeProductFromCart(){
        removeFromCartBtn.click();
        return this;
    }
    public void navigateToProducts(){
        productsPageBtn.click();
    }
}
