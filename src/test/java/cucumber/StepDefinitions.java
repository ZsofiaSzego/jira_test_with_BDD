package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class StepDefinitions {
    Util util;
    DashboardPage dashBoardPage;
    MainPage mainPage;
    LogInpage logInpage;
    LogOutPage logOutPage;
    CreateIssuePage createIssuePage;
    EditIssuePage editIssuePage;
    IssuePage issuePage;

    @Given("the user is on the {string} page.")
    public void UserIsOnPage(String url) {
        util = new Util();
        util.setUp(url);
    }

    @When("the user enters {string} as username and {string} as password.")
    public void EntersUsernameAndPassword(String username, String password) {
        dashBoardPage = new DashboardPage(util.getDriver());
        dashBoardPage.fillUserForm(username);
        dashBoardPage.fillPasswordForm(password);
    }

    @When("the user clicks on the Log in button.")
    public void UserClicksOnButton() {
        dashBoardPage.clickLoginButton();
    }

    @When("the user logs in with {string}, {string}.")
    public void UserLogsIn(String username, String password){
        logInpage = new LogInpage(util.getDriver());
        logInpage.logIn(username, password);
        util.implicitWait();
        logInpage.navigate();
    }

    @Then("the logging in was {string} with {string}.")
    public void LoggingInSuccessWas(String success, String user){
        switch (success){
            case "successful":
                mainPage = new MainPage(util.getDriver());
                util.waitForElement(mainPage.getAvatar());
                Assertions.assertEquals(user, mainPage.getUserNameFromAvatar());
                break;
            case "unsuccessful":
                util.waitForElement(dashBoardPage.getLoginError());
                Assertions.assertTrue(dashBoardPage.isUnsuccessfulLogIn());
                break;
        }
        util.tearDown();
    }

    @Given("the user is logged in as {string}.")
    public void UserIsLoggedIn(String user){
        util = new Util();
        util.setUp("https://jira-auto.codecool.metastage.net/");
        dashBoardPage = new DashboardPage(util.getDriver());
        dashBoardPage.login(user, "password");
        mainPage = new MainPage(util.getDriver());
        util.waitForElement(mainPage.getAvatar());
    }

    @When("the user clicks on they avatar and log out.")
    public void ClicksOnAvatar() {
        mainPage.clickAvatar();
        util.waitForElement(mainPage.getLogoutTab());
        mainPage.clickLogOutTab();
    }

    @Then("they can see a log out message.")
    public void CanSeeALogOutMessage(){
        logOutPage = new LogOutPage(util.getDriver());
        Assertions.assertTrue(logOutPage.isErrorMessageDisplayed());
    }

    @Then("they can not access they profile page.")
    public void canNotAccessProfilePage(){
        mainPage.navigateToProfile();
        util.waitForElement(mainPage.getAccessErrorMessage());
        Assertions.assertTrue(mainPage.isAccessErrorDisplayed());
        util.tearDown();
    }

    @And("an issue with {string} as summary {string} as type is exist in project {string}.")
    public void anIssueIsExistInProject(String summary, String type, String project) {
        issuePage = new IssuePage(util.getDriver());
        issuePage.navigateToProject(project);
        createIssuePage = new CreateIssuePage(util.getDriver());
        util.waitForElement(createIssuePage.getCreateButton());
        createIssuePage.createIssue(type, summary);
        util.refresh();
        issuePage.navigateToLastCreatedIssue();
    }

    @When("change issue summary to {string}.")
    public void changeIssueSummaryTo(String newSummary) {
        issuePage.clickOnEditIssue();
        editIssuePage = new EditIssuePage(util.getDriver());
        util.waitForElement(editIssuePage.getSummaryField());
        editIssuePage.editSummaryField(newSummary);
        util.refresh();
    }

    @Then("issue has {string} in summary.")
    public void issueHasInSummary(String newSummary) {
        Assertions.assertEquals(newSummary, issuePage.getSummaryVal());
        issuePage.deleteCreatedIssue();
    }

}
