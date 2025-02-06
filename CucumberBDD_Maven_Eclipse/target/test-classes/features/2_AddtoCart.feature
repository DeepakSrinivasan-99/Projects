Feature: Search and add the product to the cart

  @LoginPage @AddToCart
  Scenario: Search and add the product to the cart
    Given the User is already logged in
    When User enters the product "Helmet" in Searchbox
    And clicks on the "helmet for bike" displaying below Searchbox
    Then Search results should be displayed
    When user clicks on the specific product
    And product landing page should be displayed
    When User clicks on the AddToCart button
    Then product will be added to the cart