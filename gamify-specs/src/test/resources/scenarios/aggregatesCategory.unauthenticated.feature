Feature: Validation of unauthenticated category aggregates

  Background:
    Given there is a Gamify server
    Then I don't authenticate

  Scenario: I can't read category aggregates
    When I GET from the api.leaderboards.name endpoint
    Then I receive a 403 status code