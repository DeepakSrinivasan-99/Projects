

Feature: Search and add the product to cart
  I want to use this template for my feature file

  Scenario Outline: Search the product in the Homepage
    Given User is already logged in the Flipkart website
    When User enters the  "<Product name>" in searchbox 
    And User clicks the Search icon
    Then
     Examples:
    | Product name |
    | Mobile 5g |

