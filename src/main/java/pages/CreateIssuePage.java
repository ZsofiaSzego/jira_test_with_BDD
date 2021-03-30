package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CreateIssuePage {

    WebDriver driver;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//textarea")
    WebElement summaryField;
    @FindBy(xpath = "//*[@class='inline-issue-create-container']//button[contains(., 'Create issue')]")
    WebElement createButton;
    @FindBy(xpath = "//*[@class='iic-widget__issue-type-selector']//button") WebElement issueTypeField;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//a[text()='Bug']") WebElement bug;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//a[text()='Story']") WebElement story;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//a[text()='Epic']") WebElement epic;
    @FindBy(xpath = "//form[@class='iic-widget__form aui']//a[text()='Task']") WebElement task;


    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public void clickCreateIssue(){ createButton.click(); }

    public void chooseIssue(String issue) {
        issueTypeField.click();
        switch (issue){
            case "Bug":
                bug.click();
                break;
            case "Story":
                story.click();
                break;
            case "Epic":
                epic.click();
                break;
            default:
                task.click();
        }
    }

    public void fillAndSubmitSummary(String summary) {
        summaryField.sendKeys(summary);
        summaryField.sendKeys(Keys.ENTER);
    }

    public void createIssue(String issue, String summary){
        clickCreateIssue();
        chooseIssue(issue);
        fillAndSubmitSummary(summary);
    }

    public WebElement getCreateButton() {
        return createButton;
    }
}

