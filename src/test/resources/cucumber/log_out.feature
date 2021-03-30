Feature: Can log out

  Scenario: Can successfully log out
    Given the user is logged in as "automation1".
    When the user clicks on they avatar and log out.
    Then they can see a log out message.
    And they can not access they profile page.
