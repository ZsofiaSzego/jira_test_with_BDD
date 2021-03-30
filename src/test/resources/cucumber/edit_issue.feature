Feature: Can edit issue summary
  Scenario: Can edit issue summary successfully
    Given the user is logged in as "automation1".
    And an issue with "issue_for_edit_test" as summary "Story" as type is exist in project "PP".
    When change issue summary to "edited_issue_for_edit_test".
    Then issue has "edited_issue_for_edit_test" in summary.