Feature: Delete all items from the cart

@LoginPage @DeleteFromCart
  Scenario: Search and add the product to the cart
    Given the User is already logged in
    When User clicks on the Cart button
    Then Cart page should be displayed
    And Delete all products from the cart