Feature: User login

  Background:
    Given there is a Gamify server

  Scenario: I cannot login with unknown application
    Given I create the credentials payload credentials
    And I authenticate with the credentials credentials payload
    When I POST to the /login endpoint
    Then I receive a 403 status code

  Scenario: I can login with a registered application
    Given I create the credentials payload credentials
    When I POST the credentials payload to the /register endpoint
    Then I receive a 201 status code
    And I read the response payload
    And I read the registrationToken payload as the token property of the response payload
    When I authenticate with the credentials credentials payload
    And I POST to the /login endpoint
    Then I receive a 200 status code
    And I read the response payload
    And I read the loginToken payload as the token property of the response payload
    And I see that registrationToken and loginToken are the same
