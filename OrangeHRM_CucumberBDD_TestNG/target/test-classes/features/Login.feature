Feature: To Check Login feature of OrangeHRM website

  Background:
    Given Admin user is in Login Page
    
  
 #@LoginPage 
  Scenario Outline: Check Login with Valid credentials
    When User enters the Valid username "<Username>" and password "<Password>"
    And click the Login button
    Then User should be navigated to the Dashboard page

  Examples:
    | Username | Password  |
    | Admin    | admin123  |

 @LoginPage
  Scenario Outline: Check Login with Valid credentials and LogOut
    When User enters the Valid username "<Username>" and password "<Password>"
    And click the Login button
    Then User should be navigated to the Dashboard page
    And User should be logged out
  Examples:
    | Username | Password  |
    | Admin    | admin123  |

  #@LoginPage
  Scenario Outline: Check Login with Invalid credentials
    When User enters the Invalid username "<Username>" and password "<Password>"
    And click the Login button
    Then an invalid login error message should be displayed
  Examples:
    | Username | Password  |
    | Admin    | admin12   |
