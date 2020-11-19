Feature: Validation of event management

  Background:
    Given there is a Gamify server

  Scenario: I can create new events
    Given I create the event payload event
    When I POST the event payload to the /events endpoint
    Then I receive a 201 status code

  Scenario: I can read all events
    When I GET from the /events endpoint
    And I read the list payload
    Then I receive a 200 status code

  Scenario: I see my posted payload
    Given I create the event payload event1
    And I POST the event1 payload to the /events endpoint
    Then I receive a 201 status code
    When I GET from the /events endpoint
    And I read the list payload
    Then I see event1 in list

  Scenario: I can post two events
    Given I create the event payload event1
    And I create the event payload event2
    And I POST the event1 payload to the /events endpoint
    And I POST the event2 payload to the /events endpoint
    When I GET from the /events endpoint
    And I read the results payload
    Then I see event1 in results
    And I see event2 in results
