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

}
