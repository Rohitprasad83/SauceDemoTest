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
    public SideBar(WebDriver driver){
        super(driver);
    }

    public SideBar openSidebar(){
        waitForElementToBeClickable(sidebarBtn);
        sidebarBtn.click();
        return this;
    }

    public SideBar closeSidebar(){
        closeSidebarBtn.click();
        return this;
    }
    public SideBar allItemsPage(){
        inventorySidebarBtn.click();
        return this;
    }

    public SideBar aboutPage(){
        aboutSidebarBtn.click();
        return this;
    }
    public SideBar logout(){
        waitForElementToBeClickable(logoutSidebarBtn);
        logoutSidebarBtn.click();
        return this;
    }
    public SideBar resetApp() {
        resetSidebarBtn.click();
        return this;
    }
}
