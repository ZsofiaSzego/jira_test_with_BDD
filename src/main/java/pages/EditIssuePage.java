package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class EditIssuePage {
    WebDriver driver;
    @FindBy(id = "summary")
    WebElement summaryField;
    @FindBy(id = "edit-issue-submit") WebElement updateButton;

    public EditIssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public void fillSummaryField (String summary) {
        summaryField.sendKeys(summary);
    }

    public void editSummaryField(String summary) {
        summaryField.clear();
        fillSummaryField(summary);
        updateButton.click();
    }

    public WebElement getSummaryField() {
        return summaryField;
    }
}
