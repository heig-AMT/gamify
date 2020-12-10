Feature: Validation of account management for categories

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

  Scenario: Two users may create a specific category with the same name
    # Both users
    When I create the category payload cat with name category

    # Post as user 1
    When I authenticate with token1 api key
    And I PUT the cat resource to the api.categories.*** endpoint

    # Post as user 2
    When I authenticate with token2 api key
    And I PUT the cat resource to the api.categories.*** endpoint
    And I receive a 204 status code

  Scenario: Two users may create a category with the same name
    # Both users
    When I create the category payload cat with name category

    # Post as user 1
    When I authenticate with token1 api key
    And I POST the cat payload to the api.categories endpoint

    # Post as user 2
    When I authenticate with token2 api key
    And I POST the cat payload to the api.categories endpoint
    And I receive a 201 status code

  Scenario: Two users will not see each other's categories
    # Both users
    When I create the category payload cat with name category

    # Post as user 1
    When I authenticate with token1 api key
    And I PUT the cat resource to the api.categories.*** endpoint

    # Post as user 2
    When I authenticate with token2 api key
    And I PUT the cat resource to the api.categories.*** endpoint

    # Read as user 2
    When I GET the payload from the api.categories endpoint
    Then I read the response payload
    And I count 1 items in response

    Scenario: A category is not only identified by its creator
      #long but necessary
      When I create the credentials payload aloy
      Then I POST the aloy payload to the api.register endpoint
      Then I read the response payload
      And I read the token1 payload as the token property of the response payload
      And I authenticate with token1 api key
      #creation of a category
      Then I create the category payload category with name cat1
      Then I PUT the category payload to the api.categories.cat1 endpoint
      Then I GET from the api.categories.cat1 endpoint
      And I read the response payload
      And I receive a 200 status code
      And I see that response and category are the same
      #deletion and re-creation of account
      When I DELETE my account aloy
      Then I create the credentials payload aloy
      Then I POST the aloy payload to the api.register endpoint
      Then I read the response payload
      And I read the token2 payload as the token property of the response payload
      And I authenticate with token2 api key
      Then I GET from the api.categories.cat1 endpoint
      And I receive a 404 status code
