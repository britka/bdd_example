Feature: Check sorting feature on products page

  Scenario Outline: Check sorting on notebooks page
    Given User is on main page
    And User login with "brit.sergey@gmail.com" user name and "12345678" password
    When User go to "Computers" main category and "Notebooks" subcategory
    And User choose sort by "<sortBy>" direction
    Then Products should be sorted as "<sortBy>"

    # This is example section
    Examples:
      | sortBy            |
      | NAME_A_TO_Z       |
      | NAME_Z_TO_A       |
      | PRICE_HIGH_TO_LOW |
      | PRICE_LOW_TO_HIGH |