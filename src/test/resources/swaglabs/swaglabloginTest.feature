Feature: login test feature

  Background:
    Given user open the swaglab app in android


    Scenario: to test the login functionality standard user
      When user enter the username "standard_user"
      And  user enter the password "secret_sauce"
      And user click on the login button
      Then user can see the dashboard
      Then user close the app



  Scenario: to test the login functionality problem user
    When user enter the username "problem_user"
    And  user enter the password "secret_sauce"
    And user click on the login button
    Then user can see the dashboard
    Then user close the app



