# Created by daniil at 12.12.2023
Feature: Character Creation

  Scenario: POST a New Character
    Given the character creation API endpoint
    When a POST request is made with a properly formatted body
    Then return a 201 status code and send back the ID of the created character

  Scenario: POST a New Character with Missing Parameters
    Given the character creation API endpoint
    When a POST request is made with missing parameters
    Then return a 400 status code with message about required parameters

  Scenario: GET an Existing Character
    Given the character fetch API endpoint
    When a GET request is made with a valid character ID
    Then return a 200 status code and the requested character data

  Scenario: GET an Non-existent Character
    Given the character fetch API endpoint
    When a GET request is made with an ID that doesn't correspond to any character
    Then return a 404 status code and a message indicating the character was not found

  Scenario: GET All Characters of User
    Given the character fetch API endpoint
    When a GET request is made with a valid user ID
    Then return a 200 status code and the requested character data

  Scenario: GET All Characters of Non-existent User
    Given the character fetch API endpoint
    When a GET request is made with an ID that doesn't correspond to any user
    Then return a 404 status code and a message indicating the user was not found