Feature: Validation of unauthenticated categories management

  Background:
    Given there is a Gamify server
    Then I don't authenticate

  # General

  Scenario: I can't read categories
    When I GET the payload from the api.categories endpoint
    Then I receive a 403 status code

  # Specific categories

  Scenario: I can't update a specific category
    Given I create the category payload category
    When I PUT the category payload to the api.categories.hello endpoint
    Then I receive a 403 status code

  Scenario: I can't read a specific category
    When I GET from the api.categories.jello endpoint
    Then I receive a 403 status code

  Scenario: I can't delete a specific category
    When I DELETE the resource api.categories.jlo
    Then I receive a 403 status code
