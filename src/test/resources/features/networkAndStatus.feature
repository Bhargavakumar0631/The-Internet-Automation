Feature: Network Status and Resource Loading
  As a user, I want to verify HTTP statuses and resource availability
  So that I can ensure backend routing and assets are healthy

  Background:
    Given the user navigates to the homepage

  @network @broken_images
  Scenario: Detecting Broken Images
    Given the user navigates to "Broken Images"
    Then 2 out of the 3 images on the page should fail to load

  @network @redirect_link
  Scenario: Redirect Link
    Given the user navigates to "Redirect Link"
    When the user clicks ‘here’ to trigger a redirect
    Then the browser URL should change to the Status Codes page

  @network @status_codes
  Scenario Outline: Validating Status Codes
    Given the user navigates to "Status Codes"
    When the user clicks on the status code "<code_number>"
    Then the page text should indicate that the server returned a "<code_number>" status

    Examples:
      | code_number |
      | 200         |
      | 301         |
      | 404         |
      | 500         |

  @network @slow_resources
  Scenario: Slow Resources handling
    When the user navigates to "Slow Resources"
    Then rogue GET request that takes 30 seconds to complete
