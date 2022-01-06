Feature: Dashboard Page
  Scenario: creating a grocery item will display the grocery item to the item list
    Given A user is logged in
    When a user inputs a item in the create item input
    Then the item will be added to the item list
  Scenario: clicking a grocery item will toggle in cart
    Given A user is logged in
    When a user clicks on an item
    Then the item will toggle in cart
  Scenario: deleting a grocery item will remove item from list
    Given A user is logged in
    When a user clicks the delete button on an item
    Then the item will be removed from the list
  Scenario: clicking the Check Out button will remove all items in cart
    Given A user is logged in
    When a user clicks the Check Out Button
    Then all in cart items will be removed