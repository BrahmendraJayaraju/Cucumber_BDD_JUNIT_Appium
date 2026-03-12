Feature: Login test feature

  @REGRES
  Scenario: Login with valid user
    When user enter the username and password
      | USERNAME      | PASSWORD     |
      | standard_user | secret_sauce |
    And user click on the login button



  @REGRES
  Scenario: Login with locked user
    When user enter the username and password
      | USERNAME        | PASSWORD     |
      | locked_out_user | secret_sauce |
    And user click on the login button
    Then validate error message


  @REGRES
  Scenario Outline: Login with multiple users
    When user enter the username "<USERNAME>" and password "<PASSWORD>"
    And user click on the login button
    #Then validate login result

    Examples:
      | USERNAME        | PASSWORD     |
      | locked_out_user | secret_sauce |
      | problem_user    | secret_sauce |
      | standard_user   | secret_sauce |