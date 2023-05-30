Feature: Demo feature
  Description: This is just a test demo feature
  zGiven - precondition
  zWhen and zAnd - test action (operation)
  zThen and zAnd - verification point

  #Background: Launch browser and get to test page
    #Given I can open web page and dismiss alert
    #When I can login into my account
    #Then I can logout from my account successfully

  #Simple test
#  Scenario: Demonstrating how Cucumber-JVM works
#    Given I can open web page and dismiss alert
#    When I can login into my account
#    Then I can logout from my account successfully

  #Parameterized test
#  Scenario: Demonstrating how Cucumber-JVM works
#    Given I can open web page and dismiss alert
#    When I can login into my account as "igortest_1" and "Test@123Test!123"
#    Then I can logout from my account successfully

  #Data-Driven tests
  Scenario Outline: Demonstrating how Cucumber-JVM works
    Given I can open web page and dismiss alert
    When I can login into my account as "<username>" and "<password>"
    Then I can logout from my account successfully
    Examples:
      | username  | password  |
      | igortest_1 | Test@123Test!123 |
      | igortest_1 | Test@123Test!123 |

   #Data-Tables
#  Scenario: Successful Login with Valid Credentials with Data Tables
#    Given I can open web page and dismiss alert
#    When I can login into my account and logout many times with Data Tables
#      | Username   | Password |
#      | igortest_1 | Test@123Test!123 |
#      | igortest_1 | Test@123Test!123 |
#    Then I'm happy now

   #Class-Objects
#  Scenario: Successful Login with Valid Credentials with Class Objects
#    Given I can open web page and dismiss alert
#    When I can login into my account with Credentials and logout many times with Class Objects
#      | Username   | Password |
#      | igortest_1 | Test@123Test!123 |
#      | igortest_1 | Test@123Test!123 |
#    Then I'm happy now