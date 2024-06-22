package org.sauceLabsLogin;

import org.testng.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.*;
import utilities.DataProviderUtils;

public class ProductTests extends BaseTest {
    @BeforeMethod
    public void navigateToProductsPage() {
        driver.get("https://www.saucedemo.com/");
        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();
    }

    @Test
    public void addToCart(){
        try{
            inventoryPage.openProductPage("Sauce Labs Bike Light");
        }
        catch (StaleElementReferenceException ignored){
        }
        productPage.addProductToCart();
        int cartCount = Integer.parseInt(sideBar.cartItems());
        Assert.assertEquals(1,cartCount);
    }

    @Test
    public void checkProductTitle(){
        try{
            inventoryPage.openProductPage("Sauce Labs Bolt T-Shirt");
        }
        catch (StaleElementReferenceException ignored){
        }

        String ProductTitle = productPage.getProductTitle();

        Assert.assertEquals("Sauce Labs Bolt T-Shirt",ProductTitle);
    }

    @Test(dataProvider = "productData", dataProviderClass = DataProviderUtils.class)
    public void testAllProductDetails(String productName, String productDesc, String productPrice){
        try{
            inventoryPage.openProductPage(productName);
        }
        catch (StaleElementReferenceException ignored){
        }
        Assert.assertEquals(productPage.getProductTitle(), productName);
        Assert.assertEquals(productPage.getProductDescription(), productDesc);
        Assert.assertEquals(productPage.getProductPrice(), productPrice);

        productPage.navigateToProducts();
    }
    @AfterMethod
    public void clearTest(){
        sideBar.openSidebar().resetApp().logout();
    }
}
