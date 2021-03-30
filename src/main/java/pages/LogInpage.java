package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogInpage {
    WebDriver driver;
    @FindBy(id = "login-form-submit")
    WebElement loginButton;
    @FindBy(id = "login-form-username")
    WebElement usernameForm;
    @FindBy(id = "login-form-password")
    WebElement passwordForm;

    public LogInpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }
    public void fillUserForm(String username){
        usernameForm.sendKeys(username);
    }

    public void fillPasswordForm(String password){
        passwordForm.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void logIn(String username, String password){
        fillUserForm(username);
        fillPasswordForm(password);
        clickLoginButton();
    }

    public void navigate(){
        driver.navigate().to("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }

}
