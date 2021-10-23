@BraveCoin
  Feature: BraveNewCoin test Scenario

  Rule: When I send a POST request, I receive a token for authentication
  @FirstTestCoin
  Scenario: As a user, I can get a token when I send a post request
    Given I've a valid key the https://bravenewcoin.p.rapidapi.com
    When I send a POST request with a valid BodyTokenCoin body to the /oauth/token
    Then I can receive a token in the response

  Scenario:  As a user, I can receive a HTTP status code 4xx
    Given I've a invalid key the https://bravenewcoin.p.rapidapi.com
    When I send a POST request with a valid body to the /oauth/token
    Then I can receive a code 4xx


  Scenario: As a user, I can send a body without "grant_type" and I receive a HTTP status code 4xx

    Given I've a valid key the https://bravenewcoin.p.rapidapi.com
    When I send a POST request without "grant_type"
    Then I can receive a code 4xx