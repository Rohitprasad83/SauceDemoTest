package org.sauceLabsLogin;

import Pages.*;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {
    private LoginPage loginPage;
    private ProductPage productPage;

    private InventoryPage inventoryPage;

    private SideBar sideBar;
    @BeforeClass
    public void initializingPages(){
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        inventoryPage = new InventoryPage(driver);
        sideBar = new SideBar(driver);
    }

    @BeforeMethod
    public void navigateToProductsPage() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();
    }

    @Test
    public void addToCart(){
        try{
            inventoryPage.openProductPage("Sauce Labs Bike Light");
        }
        catch (StaleElementReferenceException ignored){
        }
        productPage.addProductToCart();
        sideBar.clickOnCart();
    }
}
