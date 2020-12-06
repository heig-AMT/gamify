# Created by David Dupraz at 05.12.2020
Feature: badges test

  Background:
    Given there is a Gamify server
    And I create the credentials payload registration
    When I POST the registration payload to the api.register endpoint
    Then I receive a 201 status code
    And I read the registrationResponse payload
    And I read the token payload as the token property of the registrationResponse payload
    And I authenticate with token api key
    Then I create the category payload category
    And I PUT the category resource to the api.categories.*** endpoint
    Then I receive a 204 status code

  Scenario: I can create a badge
    Given I create the badge badge1 linked to category category
    When I PUT the badge1 badge to the api\.badges endpoint
    Then I receive a 204 status code
    #When I just GET from the badges endpoint
    #Then I receive a 200 status code
    When I GET badge1 from the badges endpoint
    Then I receive a 200 status code