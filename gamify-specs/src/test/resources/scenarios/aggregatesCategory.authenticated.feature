Feature: Validation of authenticated aggregates

  Background:
    Given there is a Gamify server
    And I create the credentials payload credentials
    When I POST the credentials payload to the api.register endpoint
    Then I read the response payload
    And I read the token payload as the token property of the response payload
    And I authenticate with token api key

  Scenario: I can't get a category aggregate for a missing category
    When I GET from the api.leaderboards.name endpoint
    Then I receive a 404 status code

  Scenario: I can get a category aggregate with no end users
    Given I create the category payload cat with name name
    When I PUT the cat payload to the api.categories.name endpoint
    Then I GET from the api.leaderboards.name endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 0 items in response

  Scenario: I can get a category aggregate with one user
    Given I create the category payload cat with name catName
    And I PUT the cat payload to the api.categories.catName endpoint
    Given I create the rule payload rule with name ruleName for category catName and event type eventName awarding 50 points
    And I PUT the rule payload to the api.rules.ruleName endpoint
    Given I create the event payload event with type eventName
    When I POST the event payload to the api.events endpoint
    Then I GET from the api.leaderboards.catName endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 1 items in response
    And The best user in the leaderboard response has 50 points

  Scenario: I can get a category aggregate with three users
    Given I create the category payload cat with name catName
    And I PUT the cat payload to the api.categories.catName endpoint
    Given I create the rule payload rule1 with name ruleName1 for category catName and event type eventName1 awarding 75 points
    And I PUT the rule1 payload to the api.rules.ruleName1 endpoint
    Given I create the rule payload rule2 with name ruleName2 for category catName and event type eventName2 awarding 100 points
    And I PUT the rule2 payload to the api.rules.ruleName2 endpoint
    Given I create the event payload event1 with type eventName1 and for user userId1
    Given I create the event payload event4 with type eventName1 and for user userId1
    Given I create the event payload event2 with type eventName2 and for user userId2
    Given I create the event payload event3 with type eventName1 and for user userId3
    When I POST the event1 payload to the api.events endpoint
    And I POST the event4 payload to the api.events endpoint
    And I POST the event2 payload to the api.events endpoint
    And I POST the event3 payload to the api.events endpoint
    Then I GET from the api.leaderboards.catName endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 3 items in response
    And The best user in the leaderboard response has 150 points