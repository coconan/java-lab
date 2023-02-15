Feature: Cash Withdrawal
  Scenario: Successful withdraw from an account in credit
    Given my account has been credited with $100.00
    When I withdraw $20
    Then $20 should be dispensed
    And the balance of my account should be $80.00

  Scenario: Unsuccessful withdrawal due to technical fault
    Given my account has been credited with $100.00
    But the cash slot has developed a fault
    When I withdraw $20
    Then I should see an out-of-order message
    And $0 should be dispensed
    And the balance of my account should be $100.00
