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
