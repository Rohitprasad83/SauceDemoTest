package org.sauceLabsLogin;

import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.SideBar;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.*;


public class InventoryTests extends BaseTest {
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private SideBar sideBar;
    @BeforeClass
    public void initializingPages(){
        loginPage = new LoginPage(driver);
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
        Thread.sleep(2000);
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
    @AfterMethod
    public void logout(){
        sideBar.openSidebar().logout();
    }
}
