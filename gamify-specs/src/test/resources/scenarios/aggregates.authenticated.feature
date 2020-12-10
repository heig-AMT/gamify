Feature: Validation of authenticated aggregates

  Background:
    Given there is a Gamify server
    And I create the credentials payload credentials
    When I POST the credentials payload to the api.register endpoint
    Then I read the response payload
    And I read the token payload as the token property of the response payload
    And I authenticate with token api key

  Scenario: I can't get a category aggregate for a missing category
    When I GET from the api.leaderboards.name endpoint
    Then I receive a 404 status code

  Scenario: I can't get a user aggregate for a missing user
    When I GET from the api.users.id endpoint
    Then I receive a 404 status code

  Scenario: I can get a category aggregate with no end users
    Given I create the category payload cat with name name
    When I PUT the cat payload to the api.categories.name endpoint
    Then I GET from the api.leaderboards.name endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 0 items in response

  Scenario: I can get a category aggregate with one user
    Given I create the category payload cat with name catName
    And I PUT the cat payload to the api.categories.catName endpoint
    Given I create the rule payload rule for category catName
    And I PUT the rule payload to the api.rules.ruleName endpoint
    Given I create the event payload event for category catName
    When I POST the event payload to the api.events endpoint
    Then I GET from the api.leaderboards.catName endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 1 items in response

  Scenario: I can get a user aggregate with no events
    Given I create the category payload cat with name name
    And I PUT the cat payload to the api.categories.name endpoint
    Then I GET from the api.users.id endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 0 items in response

  Scenario: I can get a user aggregate with one event
    Given I create the category payload cat with name catName
    And I PUT the cat payload to the api.categories.catName endpoint
    Given I create the rule payload rule for category catName
    And I PUT the rule payload to the api.rules.ruleName endpoint
    Given I create the event payload event for category catName and for user id
    When I POST the event payload to the api.events endpoint
    Then I GET from the api.users.id endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 1 items in response