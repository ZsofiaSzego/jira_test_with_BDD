package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MainPage {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"header-details-user-fullname\"]")
    WebElement avatar;
    @FindBy(id = "log_out")
    WebElement logoutTab;
    @FindBy(xpath = "//p[text()='You must log in to access this page.']")
    WebElement accessErrorMessage;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public String getUserNameFromAvatar(){
        return avatar.getAttribute("data-username");
    }

    public void clickAvatar(){
        avatar.click();
    }

    public void clickLogOutTab(){
        logoutTab.click();
    }

    public void navigateToProfile(){
        driver.navigate().to("https://jira-auto.codecool.metastage.net/secure/ViewProfile.jspa");
    }

    public boolean isAccessErrorDisplayed(){
        return accessErrorMessage.isDisplayed();
    }

    public WebElement getAvatar() {
        return avatar;
    }

    public WebElement getLogoutTab() {
        return logoutTab;
    }

    public WebElement getAccessErrorMessage() {
        return accessErrorMessage;
    }
}
