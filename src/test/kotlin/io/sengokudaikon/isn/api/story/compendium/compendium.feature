# Created by daniil at 12.12.2023
Feature: Ancestry Fetching

  Scenario: Get Ancestry by ID
    When a GET request is made with a valid ancestry ID
    Then return a 200 status code and send back the ancestry

  Scenario: Get Ancestry by Name
    When a GET request is made with a valid ancestry name
    Then return a 200 status code and send back the ancestry

  Scenario: Get list of Ancestries
    When a GET request is made to the list endpoint
    Then return a 200 status code and send back the list of ancestries

  Scenario: Get ancestry by ID with invalid ID
    When a GET request is made with an invalid ancestry ID
    Then return a 404 status code

  Scenario: Get ancestry by Name with invalid Name
    When a GET request is made with an invalid ancestry name
    Then return a 404 status code