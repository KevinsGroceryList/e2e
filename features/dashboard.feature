Feature: Dashboard Page
  Scenario: creating a grocery item will display the grocery item to the item list
    Given A user is logged in
    When a user inputs a item in the create item input
    Then the item will be added to the item list