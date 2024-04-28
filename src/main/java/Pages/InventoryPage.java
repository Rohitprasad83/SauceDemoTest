package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class InventoryPage {
    private WebDriver wd;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@data-test='title']")
    private WebElement title;

    @FindBy(xpath = "//*[@data-test='inventory-item-name']")
    private List<WebElement> products;

    @FindBy(xpath = "//select[@data-test='product-sort-container']")
    private WebElement sortDropDown;

    @FindBy(xpath = "//*[@data-test='shopping-cart-badge']")
    private WebElement cartItemsCount;

    public InventoryPage(WebDriver wd){
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }

    public InventoryPage acceptPasswordAlert(){
        Alert passwordAlert = wd.switchTo().alert();
        passwordAlert.accept();
        return this;
    }

    public String inventoryPageTitle(){
        return title.getText();
    }

    public InventoryPage openProductPage(String productName){
        for(WebElement i : products){
            if(productName.equals(i.getText())){
                i.click();
            }
        }

        return this;
    }

    public InventoryPage clickSortProducts(String sortBy) throws InterruptedException {
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@data-test='product-sort-container']")));
        Select sortProducts = new Select(sortDropDown);

        sortProducts.selectByValue(sortBy);
        return this;
    }

    public InventoryPage addProductToCart(String productName){
        String id = "add-to-cart-"+productName.toLowerCase().replace(" ", "-");
        WebElement addToCart = wd.findElement(By.id(id));

        addToCart.click();
        return this;
    }

    public String cartItems(){
        return cartItemsCount.getText();
    }

}
