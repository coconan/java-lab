Feature: Cash Withdrawal
  Scenario: Successful withdraw from an account in credit
    Given my account has been credited with $100.00
    When I withdraw $20
    Then $20 should be dispensed
    And the balance of my account should be $80.00

  Scenario: Unsuccessful withdrawal due to technical fault
    Given my account is in credit
    But the cash slot has developed a fault
    When I request some of my money
    Then I should see an out-of-order message
    And $0 should be dispensed
    And the balance of my account should not be changed

  Scenario: Unsuccessful withdrawal due to insufficient ATM funds
    Given my account is in credit
    And the ATM contains $10
    When I withdraw $20
    Then I should see an ask-for-less-money message
    And $0 should be dispensed
    And the balance of my account should not be changed

  Scenario: Unsuccessful withdrawal due to insufficient ATM funds
    Given my account is in credit
    And the ATM contains $10
    When I type $20
    Then I should see an ask-for-less-money message
