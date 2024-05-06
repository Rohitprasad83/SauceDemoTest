package org.sauceLabsLogin;

import java.lang.*;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;
public class LoginTests extends BaseTest{
    private LoginPage loginPage;
    @BeforeClass
    public void intializingPages(){
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void visitPage(){
        loginPage.navigateTo("https://www.saucedemo.com/");
    }

    @Test
    public void checkTitle(){
        String url = loginPage.getPageTitle();
        Assert.assertEquals(url, "Swag Labs", "Incorrect Title");
    }

    @Test
    public void unsuccessfulLoginIncorrectPassword() throws InterruptedException {
        loginPage.setUserName("standard_user")
                .setPassword("1234")
                .clickSubmit();

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testSuccessfulLogin(){
        
        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
    }
}
