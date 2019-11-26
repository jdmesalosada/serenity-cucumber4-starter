Feature: User sign up

  Scenario: User sign up successfully

    Given Julian is an user that wants to manage their bank products
    When he sends the required information to sign up
    Then he should get a virtual account to manage their products