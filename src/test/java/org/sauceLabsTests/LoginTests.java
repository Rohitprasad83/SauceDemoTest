package org.sauceLabsTests;

import java.lang.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.DataProviderUtils;

public class LoginTests extends BaseTest{
    @BeforeMethod
    public void visitPage(){
        loginPage.navigateTo(configReader.getUrl());
    }
    @Test
    public void testSuccessfulLogin(){
        String username = configReader.getUsername();
        String password = configReader.getPassword();
        loginPage.login(username, password);

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
        sideBar.openSidebar().resetApp().logout();
    }

    @Test
    public void unsuccessfulLoginIncorrectPassword() throws InterruptedException {
        String username = configReader.getUsername();
        String password = "12345";
        loginPage.login(username, password);

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void unsuccessfulLoginIncorrectUsername() throws InterruptedException {
        String username = "username";
        String password = configReader.getPassword();
        loginPage.login(username, password);

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }
    @Test
    public void unsuccessfulLoginIncorrectUsernameAndPassword() throws InterruptedException {
        String username = "username";
        String password = "12345";
        loginPage.login(username, password);

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void unsuccessfulLoginOnBlankUsername() throws InterruptedException {
        String username = "";
        String password = configReader.getPassword();
        loginPage.login(username, password);

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void unsuccessfulLoginOnBlankPassword() throws InterruptedException {
        String username = configReader.getUsername();
        String password = "";
        loginPage.login(username, password);

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public void lockedOutUserLogin() throws InterruptedException {
        String username = "locked_out_user";
        String password = "secret_sauce";
        loginPage.login(username, password);

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUtils.class)
    public void testFromExcel(String username, String password, String expectedResults) throws InterruptedException {
        loginPage.login(username, password);

        if (expectedResults.equalsIgnoreCase("Epic sadface: Sorry, this user has been locked out.")) {
            Assert.assertEquals(loginPage.loginError(), expectedResults);
        } else {
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
            sideBar.openSidebar().resetApp().logout();
        }
    }
}
