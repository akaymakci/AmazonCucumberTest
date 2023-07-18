Feature: Search Functionality on Amazon

  Background:
    Given Navigate to Amazon
#    When Enter username and password and click login button
#    Then User should login successfully

  Scenario Outline: As a customer when I search for Nintendo, I want to see if the third option on the second page is available for purchase and can be added to the cart.
    And search for Product
      | searchBar | <Product> |

    When navigate to page number requested page
      | nextPage | <pageNumber> |

    And select item on the requested row
      | itemNumber | <itemNumber> |

    Then the user is able to add it to the cart
      | addToCart |

    And Success message should be displayed

    Examples:
      | Product  | pageNumber | itemNumber |
      | Nintendo | 6          | 3          |
