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

  Scenario: can't delete a missing rule
    When I DELETE the resource api.rules.roulette
    Then I receive a 404 status code

  Scenario: can delete a rule I've added
    When I create the category payload category with name cat
    And I create the rule payload rule with name roulette for category cat
    And I POST the category payload to the api.categories endpoint
    And I receive a 201 status code
    And I POST the rule payload to the api.rules endpoint
    And I receive a 201 status code
    And I DELETE the resource api.rules.roulette
    Then I receive a 204 status code
    When I GET the payload from the api.rules endpoint
    And I read the response payload
    Then I count 0 items in response

  Scenario: can't read missing rules
    When I GET from the api.rules.roulette endpoint
    Then I receive a 404 status code

  Scenario: can read an existing rule
    When I create the category payload category with name cat
    And I create the rule payload rule with name roulette for category cat
    And I POST the category payload to the api.categories endpoint
    And I POST the rule payload to the api.rules endpoint
    And I GET from the api.rules.roulette endpoint
    Then I read the response payload
    And I receive a 200 status code
    And I see that response and rule are the same

  Scenario: can put a non-existing rule
    When I create the category payload category with name cat
    And I create the rule payload rule with name roulette for category cat
    And I POST the category payload to the api.categories endpoint
    And I PUT the rule resource to the api.rules.*** endpoint
    And I receive a 201 status code
    And I GET from the api.rules.roulette endpoint
    Then I read the response payload
    And I receive a 200 status code
    And I see that response and rule are the same

  Scenario: put is idempotent
    When I create the category payload category with name cat
    And I create the rule payload rule with name roulette for category cat
    And I POST the category payload to the api.categories endpoint
    And I PUT the rule resource to the api.rules.*** endpoint
    And I receive a 201 status code
    And I PUT the rule resource to the api.rules.*** endpoint
    And I receive a 204 status code
    And I PUT the rule resource to the api.rules.*** endpoint
    And I receive a 204 status code
    And I GET from the api.rules.roulette endpoint
    Then I read the response payload
    And I receive a 200 status code
    And I see that response and rule are the same

  Scenario: put updates values
    When I create the category payload category with name cat
    And I create the rule payload rule1 with name roulette for category cat
    And I create the rule payload rule2 with name roulette for category cat
    And I POST the category payload to the api.categories endpoint
    And I PUT the rule1 resource to the api.rules.*** endpoint
    And I PUT the rule2 resource to the api.rules.*** endpoint
    And I receive a 204 status code
    And I GET from the api.rules.roulette endpoint
    Then I read the response payload
    And I receive a 200 status code
    And I see that response and rule1 are not the same
    And I see that response and rule2 are the same
