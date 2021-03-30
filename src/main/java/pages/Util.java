package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Util {
    WebDriver driver;
    WebDriverWait wait;

    public Util() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver,3);
    }

    public void setUp(String url){
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void tearDown(){
        driver.quit();
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public void waitForElement(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebDriver.Timeouts implicitWait() {
        return driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
