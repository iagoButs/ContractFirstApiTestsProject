Feature: API test For users

  @DP-T2  # this is the testCase Id in jira Zephyr Scale testCaseManamgment tools
  Scenario Outline: Create User with various data
    Given set users data: <id>, <firstName>, <lastName>, <email>, <dateOfBirth>, <documentId>, <countryOfIssue>, <validUntil>
    When send the POST request to create User
    Then the Response status code should be <statusCode>
    And if StatusCode Is 400, validate the error message

    Examples:
      | id  | firstName | lastName | email           | dateOfBirth  | documentId | countryOfIssue | validUntil   | statusCode |
      | "1" | "john"    | "doe"    | "john@mail.com" | "1999-08-29" | "234AC324" | "DE"           | "2019-08-29" | 201        |
      | "1" | "john"    | "doe"    | "john@mail.com" | "1999-08-29" | "234AC324" | "DEU"          | "2019-08-29" | 400        |
      | "1" | "john"    | "doe"    | "john@mail.com" | "1999-08-29" | "234AC324" | "DE"           | ""           | 400        |
      | "1" | "john"    | "doe"    | "john@mail.com" | "1999-08-29" | "234AC324" | ""             | "2019-08-29" | 400        |
      | "1" | "john"    | "doe"    | "john@mail.com" | "1999-08-29" | ""         | "DE"           | "2019-08-29" | 400        |
      | "1" | "john"    | "doe"    | "john@mail.com" | ""           | "234AC324" | "DE"           | "2019-08-29" | 400        |
      | "1" | "john"    | "doe"    | ""              | "1999-08-29" | "234AC324" | "DE"           | "2019-08-29" | 400        |
      | "1" | "john"    | ""       | "john@mail.com" | "1999-08-29" | "234AC324" | "DE"           | "2019-08-29" | 400        |
      | "1" | ""        | "doe"    | "john@mail.com" | "1999-08-29" | "234AC324" | "DE"           | "2019-08-29" | 400        |
      | ""  | "john"    | "doe"    | "john@mail.com" | "1999-08-29" | "234AC324" | "DE"           | "2019-08-29" | 201        |

  @DP-T3
  Scenario: get User byId
    Given send the GET request with user Id "string" to retrieve user informacion
    Then the Response status code should be 200
    And the response should contain this user details:
      | key            | value            |
      | id             | string           |
      | firstName      | string           |
      | lastName       | string           |
      | email          | user@example.com |
      | dateOfBirth    | 2019-08-24       |
      | documentId     | string           |
      | countryOfIssue | string           |
      | validUntil     | 2019-08-24       |


  @DP-T4
  Scenario Outline: update User with various data
    Given set users data for update: <id>, <firstName>, <lastName>, <email>, <dateOfBirth>, <documentId>, <countryOfIssue>, <validUntil>
    When send the PUT request to update User with user id "string"
    Then the Response status code should be <statusCode>

    Examples:
      | id  | firstName | lastName | email           | dateOfBirth  | documentId | countryOfIssue | validUntil   | statusCode |
      | "1" | "jane"    | "smith"  | "jane@mail.com" | "1990-07-29" | "234AC124" | "US"           | "2027-04-29" | 200        |
      | "1" | "jane"    | "smith"  | "jane@mail.com" | "1990-07-29" | "234AC124" | "USA"          | "2027-04-29" | 400        |
      | "1" | "jane"    | "smith"  | "jane@mail.com" | "1990-07-29" | "234AC124" | "US"           | ""           | 400        |
      | "1" | "jane"    | "smith"  | "jane@mail.com" | "1990-07-29" | "234AC124" | ""             | "2027-04-29" | 400        |
      | "1" | "jane"    | "smith"  | "jane@mail.com" | "1990-07-29" | ""         | "US"           | "2027-04-29" | 400        |
      | "1" | "jane"    | "smith"  | "jane@mail.com" | ""           | "234AC124" | "US"           | "2027-04-29" | 400        |
      | "1" | "jane"    | "smith"  | ""              | "1990-07-29" | "234AC124" | "US"           | "2027-04-29" | 400        |
      | "1" | "jane"    | ""       | "jane@mail.com" | "1990-07-29" | "234AC124" | "US"           | "2027-04-29" | 400        |
      | "1" | ""        | "smith"  | "jane@mail.com" | "1990-07-29" | "234AC124" | "US"           | "2027-04-29" | 400        |


  @DP-T1
  Scenario: delete User By Id
    Given send delete Request with userId "string"
    Then the Response status code should be 204
