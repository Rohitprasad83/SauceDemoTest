package org.sauceLabsLogin;

import java.lang.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {
    WebDriver wd;
    @BeforeSuite
    public void setup(){
        wd = new ChromeDriver();
        wd.get("https://www.saucedemo.com/");
    }
    @Test
    public void checkTitle(){
        String url = wd.getTitle();
        Assert.assertEquals(url, "Swag Labs", "Incorrect Title");
    }
}
