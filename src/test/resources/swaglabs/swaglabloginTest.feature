Feature: login test feature


  @REGRES
  Scenario: to test the login functionality standard user
      When user enter the username "standard_user"
      And  user enter the password "secret_sauce"
      And user click on the login button
      Then user can see the dashboard






  Scenario: to test ios testcase 1
    When user enter the username "standard_user"
    And  user enter the password "secret_sauce"
    And user click on the login button



  Scenario: to test ios testcase 2
    When user enter the username "standard_user"
    And  user enter the password "secret_sauce"
    And user click on the login button

  @REGRES
  Scenario: to test the login functionality problem user
    When user enter the username "problem_user"
    And  user enter the password "secret_sauce"
    And user click on the login button
    Then user can see the dashboard




  Scenario: to test the login functionality locked user
    When user enter the username  and password
    |USERNAME       |PASSWORD    |
    |locked_out_user|secret_sauce|


    And user click on the login button

    Then validate error message

  @sanity
  Scenario: to test the login functionality locked and problem user
    When user enter the username  and password
      |USERNAME       |PASSWORD    |
      |locked_out_user|secret_sauce|
      |problem_user   |secret_sauce|

    And user click on the login button

    Then validate error message



  Scenario Outline: to test the login module in application with different sets of values

    And user click on the login button
    Then user can see the dashboard
    Then user close the app

    Examples:
      |USERNAME       |PASSWORD    |
      |locked_out_user|secret_sauce|
      |problem_user   |secret_sauce|
      |standard_user  |secret_sauce|