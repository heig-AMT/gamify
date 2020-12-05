Feature: Validation of authenticated categories management

  Background:
    Given there is a Gamify server
    And I create the credentials payload credentials
    When I POST the credentials payload to the api.register endpoint
    Then I read the response payload
    And I read the token payload as the token property of the response payload
    And I authenticate with token api key

  Scenario: I can create a new category
    Given I create the category payload category with name jarjar
    And I POST the category payload to the api.categories endpoint
    Then I receive a 201 status code
    When I GET the payload from the api.categories endpoint
    And I read the response payload
    Then I count 1 items in response
    When I GET from the api.categories.jarjar endpoint
    Then I receive a 200 status code

  Scenario: I can't create two identical categories
    Given I create the category payload category
    And I POST the category payload to the api.categories endpoint
    And I POST the category payload to the api.categories endpoint
    Then I receive a 409 status code
    When I GET the payload from the api.categories endpoint
    And I read the response payload
    Then I count 1 items in response

  Scenario: I can create multiple categories
    Given I create the category payload cat1
    Given I create the category payload cat2
    Given I create the category payload cat3
    And I POST the cat1 payload to the api.categories endpoint
    And I POST the cat2 payload to the api.categories endpoint
    And I POST the cat3 payload to the api.categories endpoint
    When I GET the payload from the api.categories endpoint
    And I read the response payload
    Then I count 3 items in response

  Scenario: I can only create well-formed categories
    Given I create the category payload category
    And I PUT the category payload to the api.categories.chocobon endpoint
    Then I receive a 400 status code

  Scenario: I can create a new specific category
    Given I create the category payload category
    And I PUT the category resource to the api.categories.*** endpoint
    Then I receive a 204 status code

  Scenario: I can create two specific categories
    Given I create the category payload cat1
    And I create the category payload cat2
    When I PUT the cat1 resource to the api.categories.*** endpoint
    And I PUT the cat2 resource to the api.categories.*** endpoint
    Then I GET the payload from the api.categories endpoint
    And I read the response payload
    And I receive a 200 status code
    And I see cat1 in response
    And I see cat2 in response

  Scenario: I can't get a missing category
    When I GET from the api.categories.chocobon endpoint
    Then I receive a 404 status code

  Scenario: I can get a specific category
    Given I create the category payload cat with name aloy
    When I PUT the cat payload to the api.categories.aloy endpoint
    Then I GET from the api.categories.aloy endpoint
    And I read the response payload
    And I receive a 200 status code
    And I see that response and cat are the same

  Scenario: I can't delete a missing category
    When I DELETE the resource api.categories.chocobon
    Then I receive a 404 status code

  Scenario: I can delete a specific category
    Given I create the category payload cat with name aloy
    When I PUT the cat payload to the api.categories.aloy endpoint
    Then I DELETE the resource api.categories.aloy
    And I receive a 204 status code
    When I GET from the api.categories.aloy endpoint
    Then I receive a 404 status code
