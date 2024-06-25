package org.sauceLabsTests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utilities.ConfigReader;
import utilities.ERFilling;
import utilities.LoggerLoad;

public class BaseTest {
    //making driver static is important to share across multiple test class.
    public static WebDriver driver;
    protected InventoryPage inventoryPage;
    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected SideBar sideBar;
    protected ProductPage productPage;
    protected CheckoutStep1Pom checkoutStep1Pom;
    protected CheckoutStep2Pom checkoutStep2Pom;
    protected CheckoutCompletePom checkoutCompletePom;
    protected ConfigReader configReader;
    protected ERFilling erfilling;
    @BeforeSuite
    public void setup() {
        configReader = new ConfigReader();
        String browser = configReader.getBrowser();
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);

        }
        else if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if(browser.equalsIgnoreCase("ie")){
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
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
        if(configReader== null){
            configReader = new ConfigReader();
        }
        erfilling = new ERFilling();
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
