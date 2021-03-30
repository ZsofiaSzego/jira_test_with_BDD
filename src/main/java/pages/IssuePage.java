package pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage {
    WebDriver driver;
    @FindBy(id = "edit-issue") WebElement editIssueButton;
    @FindBy(id = "type-val") WebElement issueTypeVal;
    @FindBy(id = "summary-val") WebElement summaryVal;
    @FindBy(id = "find_link") WebElement issuesButton;
    @FindBy(xpath = "//*[@id='issues_history_main']//li[1]") WebElement recentIssue;
    @FindBy(id = "opsbar-operations_more") WebElement moreButton;
    @FindBy(id = "delete-issue") WebElement deleteButton;
    @FindBy(id = "delete-issue-submit") WebElement deleteConfirm;

    public IssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
    }

    public void navigateToProject(String project){
        driver.navigate().to("https://jira-auto.codecool.metastage.net/projects/"+project+"/issues");
    }

    public void navigateToLastCreatedIssue(){
        acceptAlert();
        issuesButton.click();
        recentIssue.click();
    }

    public void acceptAlert(){
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ignored){ }
    }

    public void clickOnEditIssue () {
        editIssueButton.click();
    }

    public void deleteCreatedIssue(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(moreButton));
        moreButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(deleteConfirm));
        deleteConfirm.click();
    }

    public String getIssueTypeVal() {
        return issueTypeVal.getText();
    }


    public String getSummaryVal() {
        return summaryVal.getText();
    }

}
