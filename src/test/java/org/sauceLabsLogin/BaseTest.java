package org.sauceLabsLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.LoggerLoad;

public class BaseTest {
    //making driver static is important to share across multiple test class.
    protected static WebDriver driver;
    @BeforeSuite
    public void setup(){
        LoggerLoad.info("Initializing ChromeDriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
