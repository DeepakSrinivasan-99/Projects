
Feature: User Login Page in Amazon
  
    #@LoginPage
    Scenario: Check Login using existing valid Mobile number and valid Password
  
    Given User is on Amazon website
    When User hovers on Signin option
    Then User click on Signin button
    And User should navigate to Signin page
    When User enters valid Mobile number "<Mobile number>"
    Then User clicks on the Continue button
    And User should enter valid Password "<Password>"
    When User clicks on the Signin button
    Then User should enter OTP and click Signin
    Then User should be logged into the Amazon Homepage 
     
   Examples:
   | Mobile number | Password |
   | 9404984 | nsas# |  
   
    @LoginPage @DeleteFromCart
    Scenario: Check Login using existing valid EmailID and valid Password
  
    Given User is on Amazon website
    When User hovers on Signin option
    Then User click on Signin button
    And User should navigate to Signin page
    When User enters valid EmailID "<EmailID>"
    Then User clicks on the Continue button
    And User should enter valid Password "<Password>"
    When User clicks on the Signin button
    Then User should enter OTP and click Signin
    Then User should be logged into the Amazon Homepage 
     
    
   Examples:
   | EmailID | Password |
   | deep@gmail.com | Deep |  
   
   
   
    #@LoginPage
    Scenario: Check Login with Invalid Mobile Number Format
  
    Given User is on Amazon website
    When User hovers on Signin option
    Then User click on Signin button
    And User should navigate to Signin page
    When User enters invalid Mobile number "<Mobile number>"
    Then User clicks on the Continue button
    And alert message should be displayed
    
    Examples:
    | Mobile number |
    | 942830302320 |
   
   
    #@LoginPage
    Scenario: Check Login using Invalid Email Id
  
    Given User is on Amazon website
    When User hovers on Signin option
    Then User click on Signin button
    And User should navigate to Signin page
    When User enters invalid Email Id "<Email ID>"
    Then User clicks on the Continue button
    And alert message should be displayed
    
    
    
    Examples:
    | Email ID |
    | djksjdkjs@jjj |
    
    #@LoginPage
    Scenario Outline: Check Login using New Email ID or Mobile Number
  
    Given User is on Amazon website
    When User hovers on Signin option
    Then User click on Signin button
    And User should navigate to Signin page
    When User enters valid new Mobile or EmailID "<inputdata>"
    Then User clicks on the Continue button
    And alert message or create account should be displayed
 
   
   Examples:
   | inputdata |
   | 6666677777 |
   | lnsdlksjs@gmail.com |
   
  
