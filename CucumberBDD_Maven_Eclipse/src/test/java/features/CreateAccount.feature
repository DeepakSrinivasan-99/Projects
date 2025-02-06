
Feature: Create a new account in Amazon website using existing Mobile number

  @CreateAccount
  Scenario: Register a new user in Amazon using existing Mobile number
    Given User is on Amazon website
    When User hovers on Signin option
    And clicks on the start here link
    Then Create account page should be displayed
    And Enter the required details in the page
    | Deepak |
    |	9344282753 |
    | gewhiw124@ |
    When User clicks on the verify Mobile number button
    Then Alert message should be displayed
     
    
    
   