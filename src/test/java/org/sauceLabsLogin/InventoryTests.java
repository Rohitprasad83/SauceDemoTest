package org.sauceLabsLogin;

import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.SideBar;
import Pages.ProductPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.*;


public class InventoryTests extends BaseTest {
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private SideBar sideBar;
    private ProductPage productPage;

    @BeforeClass
    public void initializingPages(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        sideBar = new SideBar(driver);
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void navigateToProductsPage() {
        driver.get("https://www.saucedemo.com/");
        loginPage.setUserName("standard_user")
                .setPassword("secret_sauce")
                .clickSubmit();
    }

    @Test(priority = 1)
    public void addProductToCart() {
        inventoryPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light")
                .addProductToCart("Sauce Labs Bolt T-Shirt")
                .addProductToCart("Sauce Labs Fleece Jacket")
                .addProductToCart("Sauce Labs Onesie")
                .addProductToCart("Test.allTheThings() T-Shirt (Red)");

        int countItemsInCart =Integer.parseInt(sideBar.cartItems());
        Assert.assertEquals(6, countItemsInCart);
    }

    @Test
    public void openProductDetails(){
        try{
            inventoryPage.openProductPage("Sauce Labs Bike Light");
        }
        catch (StaleElementReferenceException ignored){
        }
        Assert.assertEquals("Sauce Labs Bike Light", productPage.getProductTitle());
    }

    @Test
    public void checkSortByNameAtoZ() throws InterruptedException {
        String sortBy = "az";
        inventoryPage.clickSortProducts(sortBy);
        Assert.assertTrue(inventoryPage.checkSorting(sortBy));
    }
    @Test
    public void checkSortByNameZtoA() throws InterruptedException {
        String sortBy = "za";
        inventoryPage.clickSortProducts(sortBy);
        Assert.assertTrue(inventoryPage.checkSorting(sortBy));
    }

    @Test
    public void checkSortByPriceLowToHigh() throws InterruptedException {
        String sortBy = "lohi";
        inventoryPage.clickSortProducts(sortBy);
        Assert.assertTrue(inventoryPage.checkSorting(sortBy));
    }

    @Test
    public void checkSortByPriceHighToLow() throws InterruptedException {
        String sortBy = "hilo";
        inventoryPage.clickSortProducts(sortBy);
        Assert.assertTrue(inventoryPage.checkSorting(sortBy));
    }
    @AfterMethod
    public void logout(){
        sideBar.openSidebar().resetApp().logout();
    }
}
