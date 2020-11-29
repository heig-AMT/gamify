# Created by David Dupraz at 26.11.2020
Feature: Implement category

  Background:
    Given there is a Gamify server

  Scenario: I can create a category
    Given I create the category payload named category1 described as new_category
    When I POST the category1 payload to the /categories endpoint
    Then I receive a 201 status code
    When I GET the category1 payload to the /categories endpoint
    Then I receive a 200 status code
    When I create the category payload named category2 described as updated_category
    And I UPDATE category1 to category2 to the /categories endpoint
    Then I receive a 201 status code
    When I DELETE the category1 payload to the /categories endpoint
    Then I receive a 200 status code
