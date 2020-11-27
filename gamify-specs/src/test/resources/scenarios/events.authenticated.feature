Feature: Validation of event management

  Background:
    Given there is a Gamify server
    And I create the registration payload registration
    When I POST the registration payload to the /register endpoint
    Then I receive a 201 status code
    And I read the registrationResponse payload
    And I read the token payload as the token property of the registrationResponse payload
    And I authenticate with token payload

  Scenario: I can create new events
    Given I create the event payload event
    When I POST the event payload to the /events endpoint
    Then I receive a 201 status code

  Scenario: I can post two events
    Given I create the event payload event1
    And I create the event payload event2
    And I POST the event1 payload to the /events endpoint
    And I POST the event2 payload to the /events endpoint
