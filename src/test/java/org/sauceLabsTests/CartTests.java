package org.sauceLabsTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    @BeforeMethod
    public void navigateToProductsPage() {
        loginPage.navigateTo(configReader.getUrl());
        String username = configReader.getUsername();
        String password = configReader.getPassword();
        loginPage.login(username, password);
    }
    @Test
    public void removeProductFromCart() {
        String productName = "Sauce Labs Bike Light";
        inventoryPage.addProductToCart(productName);
        sideBar.clickOnCart();
        cartPage.removeItem(productName);
    }

    @Test(priority = 1)
    public void removeAllProductsFromCart() {
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
        sideBar.openSidebar().resetApp().logout();
    }
}
