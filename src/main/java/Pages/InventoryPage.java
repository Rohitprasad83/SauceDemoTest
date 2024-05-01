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

    @FindBy(id = "react-burger-menu-btn")
    private WebElement sidebarBtn;

    @FindBy(id = "react-burger-close-btn")
    private WebElement closeSidebarBtn;

    @FindBy(xpath = "//*[@data-test='inventory-sidebar-link']")
    private WebElement inventorySidebarBtn;

    @FindBy(xpath = "//*[@data-test='about-sidebar-link']")
    private WebElement aboutSidebarBtn;

    @FindBy(xpath = "//*[@data-test='logout-sidebar-link']")
    private WebElement logoutSidebarBtn;

    @FindBy(xpath = "//*[@data-test='reset-sidebar-link']")
    private WebElement resetSidebarBtn;

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
    
    public InventoryPage openSidebar(){
        sidebarBtn.click();
        return this;
    }

    public InventoryPage closeSidebar(){
        closeSidebarBtn.click();
        return this;
    }
    public InventoryPage allItemsPage(){
        inventorySidebarBtn.click();
        return this;
    }

    public InventoryPage aboutPage(){
        aboutSidebarBtn.click();
        return this;
    }
    public InventoryPage logout(){
        logoutSidebarBtn.click();
        return this;
    }
    public InventoryPage resetApp(){
        resetSidebarBtn.click();
        return this;
    }
    

}
