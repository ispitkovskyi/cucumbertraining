Feature: Searching for a local eSIM
          As an end user
          In order to connect to the internet
          I want to find and buy a local eSIM


  Scenario: eSIM search for Canada
    Given I am on the Airalo homepage
    When I search Airalo for "Canada"
    Then I should see "Canada" listed as a "Local" option
    And I close the browser