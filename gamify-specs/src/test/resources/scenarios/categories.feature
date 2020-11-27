# Created by David Dupraz at 26.11.2020
Feature: Implement category

  Background:
    Given there is a Gamify server

  Scenario: I can create a category
    Given I create the category payload category1
    When I POST the category1 payload to the /categories endpoint
    Then I receive a 201 status code
    When I GET the category1 payload to the /categories endpoint
    Then I receive a 200 status code
