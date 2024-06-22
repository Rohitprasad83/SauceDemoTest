package org.sauceLabsLogin;

import java.lang.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.DataProviderUtils;

public class LoginTests extends BaseTest{
    @BeforeMethod
    public void visitPage(){
        loginPage.navigateTo("https://www.saucedemo.com/");
    }


    @Test
    public void testSuccessfulLogin(){

        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
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
    public void unsuccessfulLoginIncorrectUsername() throws InterruptedException {
        loginPage.setUserName("abcasmf")
                .setPassword("secret_sauce")
                .clickSubmit();

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }
    @Test
    public void unsuccessfulLoginIncorrectUsernameAndPassword() throws InterruptedException {
        loginPage.setUserName("abcasmf")
                .setPassword("1234")
                .clickSubmit();

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void unsuccessfulLoginOnBlankUsername() throws InterruptedException {
        loginPage.setUserName("")
                .setPassword("1234")
                .clickSubmit();

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void unsuccessfulLoginOnBlankPassword() throws InterruptedException {
        loginPage.setUserName("standard_user")
                .setPassword("")
                .clickSubmit();

        String errorMessage = loginPage.loginError();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public void lockedOutUserLogin() throws InterruptedException {
        loginPage.setUserName("locked_out_user")
                .setPassword("secret_sauce")
                .clickSubmit();

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
            sideBar.openSidebar().logout();
        }
    }
}
