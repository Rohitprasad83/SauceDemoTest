package org.sauceLabsLogin;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ERFilling;
import utilities.LoggerLoad;

import java.io.IOException;

public class CheckoutTests extends BaseTest{
    private ERFilling erfilling;
    @BeforeClass
    public void setupER(){
        erfilling = new ERFilling();
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

    @Test
    public void E2ETest() throws IOException, InterruptedException {
        inventoryPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light")
                .addProductToCart("Sauce Labs Bolt T-Shirt")
                .addProductToCart("Sauce Labs Fleece Jacket")
                .addProductToCart("Sauce Labs Onesie");
        sideBar.clickOnCart();
        cartPage.checkout();
        checkoutStep1Pom.setFirstName("Rohit").setLastName("Prasad").setPostalCode("700028").clickContinueShopping();

        double itemPrice = Double.parseDouble(checkoutStep2Pom.subTotalAmount().substring(checkoutStep2Pom.subTotalAmount().indexOf("$")+1));
        double taxAmount = Double.parseDouble(checkoutStep2Pom.taxAmount().substring(checkoutStep2Pom.taxAmount().indexOf("$")+1));
        double totalAmount = Double.parseDouble(checkoutStep2Pom.totalAmount().substring(checkoutStep2Pom.totalAmount().indexOf("$")+1));
        String ERPath = "C:\\Users\\pdroh\\IdeaProjects\\SauceDemoTest\\src\\test\\resources\\ER.xlsx";
        erfilling.enteringValuesInER(itemPrice, ERPath);
        erfilling.makeERCopy(ERPath,"copy");
        double ERTaxAmount = erfilling.getTaxAmount();
        double ERTotalAmount = erfilling.getTotalAmount();
        LoggerLoad.info("ER Tax Amount " + String.valueOf(ERTaxAmount));
        Assert.assertEquals(taxAmount, ERTaxAmount);

        LoggerLoad.info("ER Total Amount " + String.valueOf(ERTotalAmount));
        Assert.assertEquals(totalAmount, ERTotalAmount);

        checkoutStep2Pom.clickFinishShopping();
        String orderConfirmation = checkoutCompletePom.checkOrderConfirmation();
        Assert.assertEquals(orderConfirmation, "Thank you for your order!");
    }
    @AfterMethod
    public void logout(){
        sideBar.openSidebar().resetApp().logout();
    }
}
