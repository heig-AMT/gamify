Feature: Validation of authenticated categories management

  Background:
    Given there is a Gamify server
    And I create the credentials payload credentials
    When I POST the credentials payload to the api.register endpoint
    Then I read the response payload
    And I read the token payload as the token property of the response payload
    And I authenticate with token api key

  Scenario: I can only create well-formed categories
    Given I create the category payload category
    And I PUT the category payload to the api.categories.chocobon endpoint
    Then I receive a 400 status code

  Scenario: I can create a new category
    Given I create the category payload category
    And I PUT the category resource to the api.categories.*** endpoint
    Then I receive a 204 status code

