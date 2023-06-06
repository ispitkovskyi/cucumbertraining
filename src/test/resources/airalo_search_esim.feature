Feature: Searching for a local eSIM
          As an end user
          In order to connect to the internet
          I want to find and buy a local eSIM


  Scenario: eSIM search for Canada
    Given I am on the Airalo homepage
    When I search Airalo for "Canada"
    Then I should see "Canada" listed as a "Local" option
    And I close the browser

  Scenario: eSIM search for Canada using Page Object
    Given I am on the Airalo homepage object
    When I search Airalo for "Canada" option
    Then I should see "Canada" item listed as a "Local" option
    And I quit the browser