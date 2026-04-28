Feature: Web Authentication Mechanisms
  As a user, I want to interact with various authentication methods
  So that I can verify secure access controls are functioning correctly

  Background:
    Given the user navigates to the homepage

  @auth @basic_auth
  Scenario: Basic Authentication
    When the user navigates to "basic_auth" with credentials "admin" and "admin" in the URL
    Then the page text should contain "Congratulations! You must have the proper credentials."

  @auth @digest_auth
  Scenario: Digest Authentication
    When the user navigates to "digest_auth" with credentials "admin" and "admin" in the URL
    Then the page text should contain "Congratulations! You must have the proper credentials."

  @auth @form_auth
  Scenario Outline: Form Authentication
    Given the user navigates to "Form Authentication"
    When the user logs in with username "<username>" and password "<password>"
    Then a flash message should contain "<expected_message>"

    Examples:
      | username | password             | expected_message               |
      | tomsmith  | SuperSecretPassword! | You logged into a secure area! |
      | admin  | admin | Your username is invalid!      |

  @auth @secure_download
  Scenario Outline: Secure File Download
    When the user navigates to "download_secure" with credentials "admin" and "admin" in the URL
    And the user clicks on any secure file link with extension "<extension>"
    Then the file should be downloaded successfully

    Examples:
    | extension |
    | .png |
    | .txt |

  @auth @forgot_password
  Scenario: Forgot Password Recovery
    Given the user navigates to "Forgot Password"
    When the user enters the email "test@example.com"
    And clicks the Retrieve Password button
    Then a server error "Internal Server Error" is expected by design