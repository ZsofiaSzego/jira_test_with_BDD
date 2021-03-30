Feature: Is log in functioning as expected in dashboard page?
  Scenario Outline: LogIn
    Given  the user is on the "https://jira-auto.codecool.metastage.net" page.
    When  the user enters <username> as username and <password> as password.
    And the user clicks on the Log in button.
    Then the logging in was <success> with <username>.
    Examples:
      | username  | password  | success        |
      | "valid"   | "valid"   | "successful"   |
      | "valid"   | "invalid" | "unsuccessful" |
      | "invalid" | "valid"   | "unsuccessful" |
      | ""        | ""        | "unsuccessful" |