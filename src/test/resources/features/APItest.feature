@FtAPI
Feature: Example for request in course

  #This is for test status code of the endpoint in the page
  Scenario: Test GET to the endpoint
    Given I send a get request to https://api.github.com
    Then I get a 200 status code

  #This evaluate quantity of user
  Scenario: Test GET to the endpoint second, this validates entries quantity
    Given I send a get request to https://jsonplaceholder.typicode.com
    Then I validate there are 10 items on the /users endpoint

  Scenario: This scenario validates the name of an user
    Given I send a get request to https://jsonplaceholder.typicode.com
    Then I validate there is a value: Elwyn.Skiles in the response at /users endpoint

  @API
  Scenario: This scenario validates the street of an user
    Given I send a get request to https://jsonplaceholder.typicode.com
    Then I validate there is a value street: Norberto Crossing in the response at /users endpoint