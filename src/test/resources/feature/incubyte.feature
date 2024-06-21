Feature: Email Action

  Background: User is on Home Page
    Given User enters Username
    And   User enters Password
    And   User Clicks on login button

  Scenario: Successful Sending Email with Valid Credentials
    Given User clicks on compose tab
    When User enters email address
    And  User enters subject
    And  User enters message body
    Then User clicks on send button
    And   User validates message sent

  Scenario: UnSuccessful Sending Email with InValid Credentials
    Given User clicks on compose tab
    When User enters invalid email address
    And  User enters subject
    And  User enters message body
    Then User clicks on send button
    And   User validates error message sent

  Scenario: Compose email without a subject
    Given User clicks on compose tab
    When User enters email address
    And I leave the subject field blank
    And  User enters message body
    Then User clicks on send button
    Then I should see an error message indicating the subject is required