package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class InventoryPage extends BasePage {
    @FindBy(xpath = "//*[@data-test='title']")
    private WebElement title;

    @FindBy(xpath = "//*[@data-test='inventory-item-name']")
    private List<WebElement> products;

    @FindBy(xpath = "//*[@data-test='inventory-item-price']")
    private List<WebElement> productsPrice;

    @FindBy(xpath = "//select[@data-test='product-sort-container']")
    private WebElement sortDropDown;


    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public InventoryPage acceptPasswordAlert(){
        Alert passwordAlert = driver.switchTo().alert();
        passwordAlert.accept();
        return this;
    }

    public String inventoryPageTitle(){
        return title.getText();
    }

    public void openProductPage(String productName){
        for(WebElement i : products) {
            if (productName.equals(i.getText())) {
                i.click();
            }
        }
    }

    public void clickSortProducts(String sortBy) throws InterruptedException {
        waitForElementToBeClickable(sortDropDown);
        Select sortProducts = new Select(sortDropDown);

        sortProducts.selectByValue(sortBy);
    }

    public InventoryPage addProductToCart(String productName){
        String id = "add-to-cart-"+productName.toLowerCase().replace(" ", "-");
        WebElement addToCart = driver.findElement(By.id(id));

        addToCart.click();
        return this;
    }

    public Boolean checkSorting(String sortBy){
        String[] productNamesArr = new String[products.size()];
        Double[] productPricesArr = new Double[productsPrice.size()];
        for(int i = 0; i < products.size(); i++){
            productNamesArr[i] = products.get(i).getText();
        }

        for(int i = 0; i < productsPrice.size(); i++){
            productPricesArr[i] = Double.parseDouble(productsPrice.get(i).getText().substring(1));
        }


        switch(sortBy){
            case "az":
                Arrays.sort(productNamesArr);
                return compareProductsName(productNamesArr);
            case "za":
                Arrays.sort(productNamesArr, Collections.reverseOrder());
                return compareProductsName(productNamesArr);
            case "lohi":
                Arrays.sort(productPricesArr);
                return compareProductsPrice(productPricesArr);
            case "hilo":
                Arrays.sort(productPricesArr, Collections.reverseOrder());
                return compareProductsPrice(productPricesArr);
            default:
                return false;
        }
    }

    public Boolean compareProductsName(String[] productNamesArr){
        for(int i = 0; i < products.size(); i++){
            System.out.println("Element Name - "+ products.get(i).getText() + " sorted Array name - "+ productNamesArr[i]);
            if(!productNamesArr[i].equalsIgnoreCase(products.get(i).getText()))
                return false;
        }
        return true;
    }

    public Boolean compareProductsPrice(Double[] productsPriceArr){
        for(int i = 0; i < productsPrice.size(); i++){
            System.out.println("Element Price - "+ productsPrice.get(i).getText().substring(1) + " sorted Array name - "+ productsPriceArr[i]);
            if(productsPriceArr[i] != Double.parseDouble(productsPrice.get(i).getText().substring(1))){
                return false;
            }
        }
        return true;
    }
}
