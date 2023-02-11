Feature: Account Management
  Scenario: Ban Unscrupulous Users
    When I behave unscrupulously
    Then I should receive an email containing:
      """
      Dear Sir,

      Your account privileges have been revoked due to your unscrupulous behavior.

      Sincerely,
      The Management
      """
    And my account should be locked
