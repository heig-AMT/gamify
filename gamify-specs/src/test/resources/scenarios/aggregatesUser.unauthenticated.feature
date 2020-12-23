Feature: Validation of unauthenticated user aggregates

  Background:
    Given there is a Gamify server
    Then I don't authenticate

  Scenario: I can't read user aggregates
    When I GET from the api.users.id endpoint
    Then I receive a 403 status code