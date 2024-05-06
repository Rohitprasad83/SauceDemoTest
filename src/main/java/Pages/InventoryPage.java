package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;


public class InventoryPage extends BasePage {
    @FindBy(xpath = "//*[@data-test='title']")
    private WebElement title;

    @FindBy(xpath = "//*[@data-test='inventory-item-name']")
    private List<WebElement> products;

    @FindBy(xpath = "//select[@data-test='product-sort-container']")
    private WebElement sortDropDown;

    @FindBy(xpath = "//*[@data-test='shopping-cart-badge']")
    private WebElement cartItemsCount;



    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public InventoryPage acceptPasswordAlert(){
        Alert passwordAlert = driver.switchTo().alert();
        passwordAlert.accept();
        return this;
    }

    public String inventoryPageTitle(){
        return title.getText();
    }

    public void openProductPage(String productName){
        for(WebElement i : products) {
            if (productName.equals(i.getText())) {
                i.click();
            }
        }
    }

    public InventoryPage clickSortProducts(String sortBy) throws InterruptedException {
        waitForElementToBeClickable(sortDropDown);
        Select sortProducts = new Select(sortDropDown);

        sortProducts.selectByValue(sortBy);
        return this;
    }

    public InventoryPage addProductToCart(String productName){
        String id = "add-to-cart-"+productName.toLowerCase().replace(" ", "-");
        WebElement addToCart = driver.findElement(By.id(id));

        addToCart.click();
        return this;
    }


    public String cartItems(){
        return cartItemsCount.getText();
    }
}
