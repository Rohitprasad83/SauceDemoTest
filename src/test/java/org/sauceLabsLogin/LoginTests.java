package org.sauceLabsLogin;

import java.lang.*;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.LoggerLoad;
public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;


    @BeforeClass
    public void setup(){
        LoggerLoad.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
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

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
