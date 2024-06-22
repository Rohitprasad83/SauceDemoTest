package org.sauceLabsTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterTests extends BaseTest {
    @BeforeMethod
    public void navigateToProductsPage() {
        loginPage.navigateTo(configReader.getUrl());
        String username = configReader.getUsername();
        String password = configReader.getPassword();
        loginPage.login(username, password);
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
