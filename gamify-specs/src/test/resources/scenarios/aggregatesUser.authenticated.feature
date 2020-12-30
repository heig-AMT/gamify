Feature: Validation of authenticated user aggregates

  Background:
    Given there is a Gamify server
    And I create the credentials payload credentials
    When I POST the credentials payload to the api.register endpoint
    Then I read the response payload
    And I read the token payload as the token property of the response payload
    And I authenticate with token api key

  Scenario: I can get a user aggregate for a user with no events
    When I GET from the api.users.userId endpoint
    Then I receive a 200 status code
    And I read the response payload
    And I count 0 items in response

  Scenario: I can get a user aggregate for a user with points in one category
    Given I create the category payload cat with name catName
    And I PUT the cat payload to the api.categories.catName endpoint
    Given I create the rule payload rule with name ruleName for category catName and event type eventName awarding 50 points
    And I PUT the rule payload to the api.rules.ruleName endpoint
    Given I create the badge badge1 linked to category catName within points range 0 to 100
    Then I PUT the badge1 badge to the api.badges endpoint
    Given I create the event payload event with type eventName and for user userId
    When I POST the event payload to the api.events endpoint
    Then I GET from the api.users.userId endpoint for category catName
    And I read the response payload
    And I receive a 200 status code
    And I count 1 items in response
    And The first point category in the user ranking response has 50 points
    And The first point category in the user ranking response has 1 badges

  Scenario: I can get a user aggregate for a user with points in several categories for one specific category
    Given I create the category payload cat1 with name catName1
    And I PUT the cat1 payload to the api.categories.catName1 endpoint
    Given I create the category payload cat2 with name catName2
    And I PUT the cat2 payload to the api.categories.catName2 endpoint
    Given I create the category payload cat3 with name catName3
    And I PUT the cat3 payload to the api.categories.catName3 endpoint
    Given I create the badge badge1 linked to category catName2 within points range 90 to 110
    Then I PUT the badge1 badge to the api.badges endpoint
    Given I create the badge badge2 linked to category catName2 within points range 0 to 50
    Then I PUT the badge2 badge to the api.badges endpoint
    Given I create the badge badge3 linked to category catName1 within points range 0 to 200
    Then I PUT the badge3 badge to the api.badges endpoint
    Given I create the rule payload rule1 with name ruleName1 for category catName1 and event type eventName1 awarding 50 points
    And I PUT the rule1 payload to the api.rules.ruleName1 endpoint
    Given I create the rule payload rule2 with name ruleName2 for category catName2 and event type eventName2 awarding 100 points
    And I PUT the rule2 payload to the api.rules.ruleName2 endpoint
    Given I create the event payload event1 with type eventName1 and for user userId1
    Given I create the event payload event2 with type eventName1 and for user userId1
    Given I create the event payload event3 with type eventName2 and for user userId1
    Given I create the event payload event4 with type eventName1 and for user userId1
    When I POST the event1 payload to the api.events endpoint
    And I POST the event2 payload to the api.events endpoint
    And I POST the event3 payload to the api.events endpoint
    And I POST the event4 payload to the api.events endpoint
    Then I GET from the api.users.userId1 endpoint for category catName2
    And I read the response payload
    And I receive a 200 status code
    And I count 1 items in response
    And The first point category in the user ranking response has 100 points
    And The first point category in the user ranking response has 1 badges

  Scenario: I can get a user aggregate for a user with points in several categories for no specific category
    Given I create the category payload cat1 with name catName1
    And I PUT the cat1 payload to the api.categories.catName1 endpoint
    Given I create the category payload cat2 with name catName2
    And I PUT the cat2 payload to the api.categories.catName2 endpoint
    Given I create the category payload cat3 with name catName3
    And I PUT the cat3 payload to the api.categories.catName3 endpoint
    Given I create the badge badge1 linked to category catName1
    Then I PUT the badge1 badge to the api.badges endpoint
    Given I create the badge badge2 linked to category catName2
    Then I PUT the badge2 badge to the api.badges endpoint
    Given I create the badge badge3 linked to category catName3
    Then I PUT the badge3 badge to the api.badges endpoint
    Given I create the rule payload rule1 with name ruleName1 for category catName1 and event type eventName1 awarding 50 points
    And I PUT the rule1 payload to the api.rules.ruleName1 endpoint
    Given I create the rule payload rule2 with name ruleName2 for category catName2 and event type eventName2 awarding 100 points
    And I PUT the rule2 payload to the api.rules.ruleName2 endpoint
    Given I create the event payload event1 with type eventName1 and for user userId1
    Given I create the event payload event2 with type eventName1 and for user userId1
    Given I create the event payload event3 with type eventName2 and for user userId1
    Given I create the event payload event4 with type eventName1 and for user userId1
    When I POST the event1 payload to the api.events endpoint
    And I POST the event2 payload to the api.events endpoint
    And I POST the event3 payload to the api.events endpoint
    And I POST the event4 payload to the api.events endpoint
    Then I GET from the api.users.userId1 endpoint
    And I read the response payload
    And I receive a 200 status code
    And I count 2 items in response
    And The first point category in the user ranking response has 150 points
    And The first point category in the user ranking response has 1 badges