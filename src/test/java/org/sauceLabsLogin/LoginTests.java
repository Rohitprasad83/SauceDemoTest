package org.sauceLabsLogin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import Pages.LoginPage;
import Pages.SideBar;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.ExcelReader;
import utilities.LoggerLoad;

public class LoginTests extends BaseTest{
    private LoginPage loginPage;
    private SideBar sideBar;
    @BeforeClass
    public void intializingPages(){

        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
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

    @Test
    public void testFromExcel() throws IOException, InterruptedException {
                ExcelReader excelReader = new ExcelReader();
                List<List<String>> LoginTestData = excelReader.readExcel("C:\\Users\\pdroh\\IdeaProjects\\SauceDemoTest\\src\\test\\resources\\LoginTestData.xlsx");
                    for(int row = 1; row < LoginTestData.size(); row++) {
                        String username = LoginTestData.get(row).get(0);
                        String password = LoginTestData.get(row).get(1);
                        String expectedResults = LoginTestData.get(row).get(2);
                        // Perform assertions or verification based on expected results
                        loginPage.login(username, password);
                        if(expectedResults.equalsIgnoreCase("Epic sadface: Sorry, this user has been locked out.")){
                             Assert.assertEquals(loginPage.loginError(), expectedResults);
                        }
                        else {
                            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
                            sideBar.openSidebar().logout();
                        }

                    }
    }
}
