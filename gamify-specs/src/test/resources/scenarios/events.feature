Feature: Validation of event management

  Background:
    Given there is a Gamify server

  Scenario: I can create new events
    Given I have an event payload
    And I have a registration payload
    When I POST the event payload to the /events endpoint
    Then I receive a 201 status code

  Scenario: I can read all events
    When I GET from the /events endpoint
    And I read the list payload
    Then I receive a 200 status code

  Scenario: I see my posted payload
    When I have an event payload
    And I POST the event payload to the /events endpoint
    Then I receive a 201 status code
    When I GET from the /events endpoint
    And I read the list payload
    Then I see event in list
