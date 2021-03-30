package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogOutPage {
    WebDriver driver;
    @FindBy(xpath = "//*[contains(text(), 'You are now logged out. Any automatic login has also been stopped.')]")
    WebElement logoutMessage;


    public LogOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public boolean isErrorMessageDisplayed(){
        return logoutMessage.isDisplayed();
    }

}
