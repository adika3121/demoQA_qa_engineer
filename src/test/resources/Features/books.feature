Feature: Books Page

  Scenario Outline: Searching for a book that doesn't exist
    Given User go to <url>
    When User in "Book Store" page
    And User search book <bookName>
    Then User see "No rows found"
    Examples:
      | url                        | bookName      |
      | "https://demoqa.com/books" | "qa engineer" |


  Scenario: Searching for a book that exist
    Given User go to "https://demoqa.com/books"
    When User in "Book Store" page
    And User search book "Git Pocket Guide"
    And User click "Git Pocket Guide"
    Then User see "Git Pocket Guide" book
