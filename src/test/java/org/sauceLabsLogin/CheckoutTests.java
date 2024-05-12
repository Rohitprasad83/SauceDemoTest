package org.sauceLabsLogin;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest{
    private InventoryPage inventoryPage;
    private LoginPage loginPage;

    private CartPage cartPage;
    private SideBar sideBar;

    private CheckoutStep1Pom checkoutStep1Pom;

    private CheckoutStep2Pom checkoutStep2Pom;

    private CheckoutCompletePom checkoutCompletePom;

    @BeforeClass
    public void initializingPages(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        sideBar = new SideBar(driver);
        checkoutStep1Pom = new CheckoutStep1Pom(driver);
        checkoutStep2Pom = new CheckoutStep2Pom(driver);
        checkoutCompletePom = new CheckoutCompletePom(driver);
    }

    @BeforeMethod
    public void visitPage(){
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testcheckout(){
        inventoryPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Fleece Jacket");
        sideBar.clickOnCart();
        cartPage.removeItem("Sauce Labs Fleece Jacket").checkout();
        checkoutStep1Pom.setFirstName("Rohit").setLastName("Prasad").setPostalCode("700028").clickContinueShopping();
        checkoutStep2Pom.clickFinishShopping();
        String orderConfirmation = checkoutCompletePom.checkOrderConfirmation();
        Assert.assertEquals(orderConfirmation, "Thank you for your order!");
    }
    @Test
    public void checkoutStep1Cancel(){
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        sideBar.clickOnCart();
        cartPage.checkout();
        checkoutStep1Pom.cancelShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test
    public void checkoutStep2Cancel(){
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        sideBar.clickOnCart();
        cartPage.checkout();
        checkoutStep1Pom.setFirstName("abcd").setLastName("avcc").setPostalCode("233");
        checkoutStep1Pom.clickContinueShopping();
        checkoutStep2Pom.cancelShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void checkErrorFirstNameIsBlank(){
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        sideBar.clickOnCart();
        cartPage.checkout();
        checkoutStep1Pom.clickContinueShopping();
        Assert.assertEquals("Error: First Name is required", checkoutStep1Pom.errorText());
    }
    @Test
    public void checkErrorLastNameIsBlank(){
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        sideBar.clickOnCart();
        cartPage.checkout();
        checkoutStep1Pom.setFirstName("Rohit").clickContinueShopping();
        Assert.assertEquals("Error: Last Name is required", checkoutStep1Pom.errorText());
    }
    @Test
    public void checkErrorPostalCodeIsBlank(){
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        sideBar.clickOnCart();
        cartPage.checkout();
        checkoutStep1Pom.setFirstName("Rohit").setLastName("Prasad").clickContinueShopping();
        Assert.assertEquals("Error: Postal Code is required", checkoutStep1Pom.errorText());
    }
    @AfterMethod
    public void logout(){
        sideBar.openSidebar().resetApp().logout();
    }
}
