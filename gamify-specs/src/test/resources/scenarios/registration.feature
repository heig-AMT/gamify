Feature: Registration and deleting apps

  Background:
    Given there is a Gamify server

  Scenario: I can register a new application
    Given I create the registration payload registration
    When I POST the registration payload to the /register endpoint
    Then I receive a 201 status code

  Scenario: I can not register twice
    Given I create the registration payload registration
    When I POST the registration payload to the /register endpoint
    Then I receive a 201 status code
    When I POST the registration payload to the /register endpoint
    Then I receive a 403 status code
