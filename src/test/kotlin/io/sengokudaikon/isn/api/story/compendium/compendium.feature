# Created by daniil at 12.12.2023
Feature: Action testing
  Scenario: Get Action by ID
    Given actions API endpoint
    When a GET request is made with a valid ID
    Then the response status should be 200

  Scenario: Get Action by Name
    Given actions API endpoint
    When a GET request is made with a valid name
    Then the response status should be 200

  Scenario: Get list of Actions
    Given actions API endpoint
    When a GET request is made to List with page and size parameters
    Then the response status should be 200

  Scenario: Get action by ID with invalid ID
    Given actions API endpoint
    When a GET request is made with an invalid ID
    Then the response status should be 404

  Scenario: Get action by Name with invalid Name
    Given actions API endpoint
    When a GET request is made with an invalid name
    Then the response status should be 404

Feature: Ancestry testing
  Scenario: Get Ancestry by ID
    Given ancestry API endpoint
    When a GET request is made with a valid ID
    Then the response status should be 200

  Scenario: Get Ancestry by Name
    Given ancestry API endpoint
    When a GET request is made with a valid name
    Then the response status should be 200

  Scenario: Get list of Ancestries
    Given ancestry API endpoint
    When a GET request is made to List with page and size parameters
    Then the response status should be 200

  Scenario: Get ancestry by ID with invalid ID
    Given ancestry API endpoint
    When a GET request is made with an invalid ID
    Then the response status should be 404

  Scenario: Get ancestry by Name with invalid Name
    Given ancestry API endpoint
    When a GET request is made with an invalid name
    Then the response status should be 404

Feature: Background testing
  Scenario: Get Background by ID
    Given background API endpoint
    When a GET request is made with a valid ID
    Then the response status should be 200

  Scenario: Get Background by Name
    Given background API endpoint
    When a GET request is made with a valid name
    Then the response status should be 200

  Scenario: Get list of Backgrounds
    Given background API endpoint
    When a GET request is made to List with page and size parameters
    Then the response status should be 200

  Scenario: Get background by ID with invalid ID
    Given background API endpoint
    When a GET request is made with an invalid ID
    Then the response status should be 404

  Scenario: Get background by Name with invalid Name
    Given background API endpoint
    When a GET request is made with an invalid name
    Then the response status should be 404