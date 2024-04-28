package org.sauceLabsLogin;

import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.LoggerLoad;

public class MakeMyTrip {
    public static void main(String args[]) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.makemytrip.com/");

        Thread.sleep(5000);

        WebElement closeModal = driver.findElement(By.xpath("//span[@data-cy='closeModal']"));

        closeModal.click();
//        driver.quit();

    }
}
