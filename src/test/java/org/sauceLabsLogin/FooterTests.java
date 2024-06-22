package org.sauceLabsLogin;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterTests extends BaseTest {
    @BeforeMethod
    public void navigateToProductsPage() {
        driver.get("https://www.saucedemo.com/");
        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();
    }
    @Test
    public void checkFooterLinksAreDisplayed(){
        Assert.assertTrue(sideBar.twiiterDisplayed());
        Assert.assertTrue(sideBar.facebookDisplayed());
        Assert.assertTrue(sideBar.linkedinDisplayed());
    }

    @AfterMethod
    public void logout(){
        sideBar.openSidebar().logout();
    }
}
