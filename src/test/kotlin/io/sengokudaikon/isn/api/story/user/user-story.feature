Feature: User Authentication
  As a user,
  I want to be able to authenticate myself,
  So that I can access protected resources.

  Scenario: Successful Registration
    Given I am on the registration page
    When I enter valid credentials
    Then I should be registered and receive a success response

  Scenario: Unsuccessful Registration
    Given I am on the registration page
    When I enter user details that are already in use
    Then I should see an error message

  Scenario: Check Email Existence
    Given I am on the registration page
    When I enter an email that is already in use
    Then I should see an error message indicating the email is already in use

  Scenario: Check Username Existence
    Given I am on the registration page
    When I enter a username that is already in use
    Then I should see an error message indicating the username is already in use