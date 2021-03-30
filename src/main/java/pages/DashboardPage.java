package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DashboardPage {
    WebDriver driver;
    @FindBy(id = "login-form-username")
    WebElement usernameForm;
    @FindBy(id = "login-form-password")
    WebElement passwordForm;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(xpath = "//div/p[contains(text(),'Sorry, your username and password are incorrect - please try again.')]")
    WebElement loginError;
    @FindBy(xpath = "//*[@id=\"user-options\"]/a")
    WebElement loginPageButton;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public void fillUserForm(String username){
        usernameForm.sendKeys(username);
    }

    public void fillPasswordForm(String password){
        passwordForm.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void login(String username, String password){
        fillUserForm(username);
        fillPasswordForm(password);
        clickLoginButton();
    }

    public boolean isUnsuccessfulLogIn(){
        return loginError.isDisplayed() && loginPageButton.isDisplayed();
    }

    public WebElement getLoginError() {
        return loginError;
    }
}
