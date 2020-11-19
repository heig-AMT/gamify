Feature: Validation of event management

  Background:
    Given there is a Gamify server

  Scenario: I can create new events
    Given I have a event payload
    And I have a registration payload
    When I POST the event payload to the /events endpoint
    # TODO : Scenario-specific data ? Then I receive a 201 status code
    # TODO : Specify the return value ?
