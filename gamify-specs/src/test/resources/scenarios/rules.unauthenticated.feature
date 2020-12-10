Feature: Validation of unauthenticated rules management

  Background:
    Given there is a Gamify server
    Then I don't authenticate

  # General

  Scenario: I can't read rules
    When I GET the payload from the api.rules endpoint
    Then I receive a 403 status code

  Scenario: I can't create a new rule
    Given I create the rule payload rule for category sample
    When I POST the rule payload to the api.rules endpoint
    Then I receive a 403 status code

  # Specific categories

  Scenario: I can't update a specific rules
    Given I create the rule payload rule for category sample
    When I PUT the rule payload to the api.rules.hello endpoint
    Then I receive a 403 status code

  Scenario: I can't read a specific rule
    When I GET from the api.rules.jello endpoint
    Then I receive a 403 status code

  Scenario: I can't delete a specific rule
    When I DELETE the resource api.rules.jlo
    Then I receive a 403 status code
