Feature: Validation of account management for events

  Background:
    Given there is a Gamify server

    # User 1
    When I create the credentials payload cred1
    Then I POST the cred1 payload to the api.register endpoint
    And I read the response payload
    And I read the token1 payload as the token property of the response payload

    # User 2
    When I create the credentials payload cred2
    Then I POST the cred2 payload to the api.register endpoint
    And I read the response payload
    And I read the token2 payload as the token property of the response payload

  Scenario: Two end users from different apps with the same name might post events
    # Both users
    When I create the category payload cat with name category
    And I create the rule payload rule1 with name r for category category and event type event awarding 123 points
    And I create the rule payload rule2 with name r for category category and event type event awarding 321 points
    And I create the event payload event with type event and for user alice

    # Post as user 1
    When I authenticate with token1 api key
    And I PUT the cat resource to the api.categories.*** endpoint
    And I PUT the rule1 resource to the api.rules.*** endpoint
    And I receive a 201 status code

    # Post as user 2
    When I authenticate with token2 api key
    And I PUT the cat resource to the api.categories.*** endpoint
    And I PUT the rule2 resource to the api.rules.*** endpoint
    And I receive a 201 status code

    # Post as user 1
    When I authenticate with token1 api key
    And I POST the event payload to the api.events endpoint

    # Post as user 2
    When I authenticate with token2 api key
    And I POST the event payload to the api.events endpoint

    # Check as user 1
    When I authenticate with token1 api key
    And I GET from the api.leaderboards.category endpoint
    And I read the leaderboard payload
    Then I count 1 items in leaderboard
    And The best user in the leaderboard leaderboard has 123 points

    # Check as user 2
    When I authenticate with token2 api key
    And I GET from the api.leaderboards.category endpoint
    And I read the leaderboard payload
    Then I count 1 items in leaderboard
    And The best user in the leaderboard leaderboard has 321 points
