package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideBar extends BasePage {
    @FindBy(id = "react-burger-menu-btn")
    private WebElement sidebarBtn;

    @FindBy(id = "react-burger-close-btn")
    private WebElement closeSidebarBtn;

    @FindBy(xpath = "//*[@data-test='inventory-sidebar-link']")
    private WebElement inventorySidebarBtn;

    @FindBy(xpath = "//*[@data-test='about-sidebar-link']")
    private WebElement aboutSidebarBtn;

    @FindBy(xpath = "//*[@data-test='logout-sidebar-link']")
    private WebElement logoutSidebarBtn;

    @FindBy(xpath = "//*[@data-test='reset-sidebar-link']")
    private WebElement resetSidebarBtn;

    @FindBy(xpath = "//*[@data-test='shopping-cart-link']")
    private WebElement cartBtn;

    @FindBy(xpath = "//*[@data-test='shopping-cart-badge']")
    private WebElement cartItemsCount;

    @FindBy(linkText = "Twitter")
    private WebElement twitterLink;

    @FindBy(linkText = "Facebook")
    private WebElement facebookLink;
    @FindBy(linkText = "LinkedIn")
    private WebElement linkedinLink;
    public SideBar(WebDriver driver){
        super(driver);
    }

    public SideBar openSidebar(){
        waitForElementToBeClickable(sidebarBtn);
        sidebarBtn.click();
        return this;
    }

    public SideBar closeSidebar(){
        waitForElementToBeClickable(closeSidebarBtn);
        closeSidebarBtn.click();
        return this;
    }
    public SideBar allItemsPage(){
        waitForElementToBeClickable(inventorySidebarBtn);
        inventorySidebarBtn.click();
        return this;
    }

    public SideBar aboutPage(){
        waitForElementToBeClickable(aboutSidebarBtn);
        aboutSidebarBtn.click();
        return this;
    }
    public SideBar logout(){
        waitForElementToBeClickable(logoutSidebarBtn);
        logoutSidebarBtn.click();
        return this;
    }
    public SideBar resetApp() {
        waitForElementToBeClickable(resetSidebarBtn);
        resetSidebarBtn.click();
        return this;
    }

    public SideBar clickOnCart(){
        waitForElementToBeClickable(cartBtn);
        cartBtn.click();
        return this;
    }

    public Boolean twiiterDisplayed(){
        return twitterLink.isDisplayed();
    }

    public Boolean facebookDisplayed(){
        return facebookLink.isDisplayed();
    }

    public Boolean linkedinDisplayed(){
        return linkedinLink.isDisplayed();
    }

    public SideBar clickTwitterFooter(){
        waitForElementToBeVisible(twitterLink);
        twitterLink.click();
        return this;
    }

    public SideBar clickFacebookFooter(){
        waitForElementToBeVisible(facebookLink);
        facebookLink.click();
        return this;
    }

    public SideBar clickLinkedInFooter(){
        waitForElementToBeVisible(linkedinLink);
        linkedinLink.click();
        return this;
    }

    public String cartItems(){
        return cartItemsCount.getText();
    }
}
