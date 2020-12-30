Feature: Validation of badges management

  Background:
    Given there is a Gamify server
    Then I create the credentials payload registration
    Then I POST the registration payload to the api.register endpoint
    Then I receive a 201 status code
    And I read the registrationResponse payload
    And I read the token payload as the token property of the registrationResponse payload
    And I authenticate with token api key
    Then I create the category payload category with name cat1
    Then I PUT the category payload to the api.categories.cat1 endpoint
    Then I receive a 204 status code

  Scenario: I can create or update a badge
    When I create the badge badge1 linked to category cat1
    Then I PUT the badge1 badge to the api.badges endpoint
    Then I receive a 204 status code

  Scenario: I can create a badge with points
    When I create the badge badge1 linked to category cat1 within points range 0 to 100
    Then I PUT the badge1 badge to the api.badges endpoint
    Then I receive a 204 status code

  Scenario: I can get one or all the badges
    When I create the badge badge1 linked to category cat1
    Then I PUT the badge1 badge to the api.badges endpoint
    When I GET from the api.badges endpoint
    Then I receive a 200 status code
    When I GET from the api.badges.badge1 endpoint
    Then I receive a 200 status code

  Scenario: I can delete a badge
    When I create the badge badge1 linked to category cat1
    Then I PUT the badge1 badge to the api.badges endpoint
    When I DELETE badge1 with api.badges.delete endpoint
    Then I receive a 204 status code

  Scenario: Delete a category deletes badges related
    When I create the badge badge1 linked to category cat1
    Then I PUT the badge1 badge to the api.badges endpoint
    Then I receive a 204 status code
    When I DELETE the resource api.categories.cat1
    Then I receive a 204 status code
    Then I GET from the api.badges endpoint
    Then I read the response payload
    And I count 0 items in response