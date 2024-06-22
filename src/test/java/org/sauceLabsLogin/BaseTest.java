package org.sauceLabsLogin;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utilities.LoggerLoad;

public class BaseTest {
    //making driver static is important to share across multiple test class.
    protected static WebDriver driver;
    protected InventoryPage inventoryPage;
    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected SideBar sideBar;
    protected ProductPage productPage;
    protected CheckoutStep1Pom checkoutStep1Pom;
    protected CheckoutStep2Pom checkoutStep2Pom;
    protected CheckoutCompletePom checkoutCompletePom;
    @BeforeSuite
    public void setup() {
        LoggerLoad.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @BeforeClass
    public void initializePages(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
        sideBar = new SideBar(driver);
        checkoutStep1Pom = new CheckoutStep1Pom(driver);
        checkoutStep2Pom = new CheckoutStep2Pom(driver);
        checkoutCompletePom = new CheckoutCompletePom(driver);
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
