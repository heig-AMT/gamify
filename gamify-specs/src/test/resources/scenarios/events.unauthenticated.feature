Feature: Validation of unauthenticated event management

  Background:
    Given there is a Gamify server
    Then I don't authenticate

  Scenario: I can't create new events
    Given I create the event payload event
    When I POST the event payload to the api.events endpoint
    Then I receive a 403 status code
