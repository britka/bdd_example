Feature: Check login and logout functionality
""" This is feature for checking
  login and logout
  feature"""

  Background:
    Given User is on main page

  Scenario: Check login
    When User login with "brit.sergey@gmail.com" user name and "12345678" password
    Then User should be logged in


  Scenario: Check logout
    Given User login with "brit.sergey@gmail.com" user name and "12345678" password
    When User log out
    Then User should be logged out
