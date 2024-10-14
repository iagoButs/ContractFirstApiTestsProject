Feature: Security tests

  Scenario Outline: authenticate with incorect credentials
    Given send get Request with credentials: <username>, <pass>
    Then status code should be 401
    And statusline should contains String: Unauthorized
    Examples:
      | username       | pass           |
      | "SomeUserName" | "SomePassword" |
      | "SomeUserName" | ""             |
      | ""             | "SomePassword" |

