Feature: Validation of authenticated rules management

  Background:
    Given there is a Gamify server
    And I create the credentials payload credentials
    When I POST the credentials payload to the api.register endpoint
    Then I read the response payload
    And I read the token payload as the token property of the response payload
    And I authenticate with token api key

  Scenario: there are no rules by default
    When I GET the payload from the api.rules endpoint
    And I read the response payload
    Then I receive a 200 status code
    And I count 0 items in response

  Scenario: can't create a new rule when the category is unknown
    When I create the rule payload rule for category unknown
    And I POST the rule payload to the api.rules endpoint
    Then I receive a 404 status code

  Scenario: can create a single category
    When I create the category payload category with name cat
    And I create the rule payload rule for category cat
    And I POST the category payload to the api.categories endpoint
    And I POST the rule payload to the api.rules endpoint
    Then I receive a 201 status code
    When I GET the payload from the api.rules endpoint
    And I read the response payload
    Then I see rule in response

  Scenario: can't create a duplicate category
    When I create the category payload category with name cat
    And I create the rule payload rule for category cat
    And I POST the category payload to the api.categories endpoint
    And I POST the rule payload to the api.rules endpoint
    And I POST the rule payload to the api.rules endpoint
    Then I receive a 409 status code
