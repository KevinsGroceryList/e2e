Feature: Register Page
  Scenario: Registering with incorrect credentials on register page will display error message
    Given A user is on the register page
    When A user inputs incorrect credentials on the register form
    Then username is already taken will be displayed to the user
  Scenario: Registering with correct credentials will redirect user to login page
    Given A user is on the register page
    When A user inputs correct credentials on the register form
    Then user will be redirected to the login page