package org.sauceLabsLogin;

import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.SideBar;
import Pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private SideBar sideBar;
    @BeforeClass
    public void initializingPages(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
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
    public void removeProductFromCart() throws InterruptedException {
        String productName = "Sauce Labs Bike Light";
        inventoryPage.addProductToCart(productName);
        sideBar.clickOnCart();
        cartPage.removeItem(productName);
    }

    @Test(priority = 1)
    public void removeAllProductsFromCart() throws InterruptedException {
        inventoryPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light")
                .addProductToCart("Sauce Labs Bolt T-Shirt")
                .addProductToCart("Sauce Labs Fleece Jacket")
                .addProductToCart("Sauce Labs Onesie")
                .addProductToCart("Test.allTheThings() T-Shirt (Red)");

        sideBar.clickOnCart();
        cartPage.removeAllItemsFromCart();
    }

    @Test
    public void checkContinueShopping(){
        sideBar.clickOnCart();
        cartPage.backToProductsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void checkCheckout(){
        sideBar.clickOnCart();
        cartPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }

    @AfterMethod
    public void logout(){
        sideBar.openSidebar().logout();
    }
}
