
Feature: User Login
  
  @LoginPage
  Scenario Outline: Successful login with valid credentials
    Given the user is on the login page
    When the user click on the Login button
    And User enters the Mobile number "<MobileNumber>"
    Then the user clicks the Request OTP button
    When User enters OTP and click verify button
    
    Examples:
    | MobileNumber |
    | 9344282753 |