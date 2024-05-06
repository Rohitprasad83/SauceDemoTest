package org.sauceLabsLogin;

import Pages.*;
import org.testng.Assert;
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
    }

    @Test
    public void testcheckout(){
//        loginPage.setUserName("standard_user").setPassword("secret_sauce").clickSubmit();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Fleece Jacket");
        sideBar.clickOnCart();
        cartPage.removeItem("Sauce Labs Fleece Jacket").checkout();
        checkoutStep1Pom.setFirstName("Rohit").setLastName("Prasad").setPostalCode("700028").clickContinueShopping();
        checkoutStep2Pom.clickFinishShopping();
        String orderConfirmation = checkoutCompletePom.checkOrderConfirmation();
        Assert.assertEquals(orderConfirmation, "Thank you for your order!");
    }
}
