package org.sauceLabsLogin;

import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.LoggerLoad;

public class InventoryTests {

    private WebDriver driver;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    @BeforeClass
    public void setup(){
        LoggerLoad.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

    }

    @BeforeMethod
    public void navigateToProductsPage() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();

        Assert.assertEquals("Products",
        inventoryPage.inventoryPageTitle());
    }

    @Test
    public void addProductToCart() {
        inventoryPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light")
                .addProductToCart("Sauce Labs Bolt T-Shirt")
                .addProductToCart("Sauce Labs Fleece Jacket")
                .addProductToCart("Sauce Labs Onesie")
                .addProductToCart("Test.allTheThings() T-Shirt (Red)");

        int countItemsInCart =Integer.parseInt(inventoryPage.cartItems());
        Assert.assertEquals(6, countItemsInCart);
    }

    @Test
    public void checkSorting() throws InterruptedException {
        inventoryPage.clickSortProducts("za");
        Thread.sleep(3000);
        inventoryPage.clickSortProducts("hilo");
    }

    @Test
    public void openProductDetails(){

        try{
            inventoryPage.openProductPage("Sauce Labs Bike Light");
        }
        catch (StaleElementReferenceException ignored){
        }
    }


//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }
}
