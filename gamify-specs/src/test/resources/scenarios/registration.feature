Feature: Registration and deleting apps

  Background:
    Given there is a Gamify server

  Scenario: I can register a new application
    Given I create the credentials payload registration
    When I POST the registration payload to the /register endpoint
    Then I receive a 201 status code

  Scenario: I can not register twice
    Given I create the credentials payload registration
    When I POST the registration payload to the /register endpoint
    Then I receive a 201 status code
    When I POST the registration payload to the /register endpoint
    Then I receive a 403 status code

  Scenario: I can read an authentication token
    Given I create the credentials payload registration
    When I POST the registration payload to the /register endpoint
    Then I receive a 201 status code
    And I read the response payload
    And I read the token payload as the token property of the response payload
    # TODO : Send the token to the server.
