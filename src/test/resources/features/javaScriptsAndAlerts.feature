Feature: JavaScript Events and Alerts
  As a user, I want to trigger JS scripts and alerts
  So that I can verify the frontend logic executes successfully

  Background:
    Given the user navigates to the homepage

  @js @javascript_alerts
  Scenario Outline: Handling JavaScript Alerts, Confirms, and Prompts
    Given the user navigates to "JavaScript Alerts"
    When the user clicks "<button_name>"
    And handles the alert by "<action>" with text "<input_text>"
    Then the result text should show "<expected_result>"

    Examples:
      | button_name         | action  | input_text | expected_result                  |
      | Click for JS Alert  | accept  |            | You successfully clicked an alert|
      | Click for JS Confirm| dismiss |            | You clicked: Cancel              |
      | Click for JS Prompt | accept  | Hello QA   | You entered: Hello QA            |

  @js @javascript_onload_error
  Scenario: JavaScript onload event error
    Given the user navigates to "JavaScript onload event error"
    Then a JavaScript error should be present in the browser console

  @js @jquery_ui_menus
  Scenario: Navigating JQuery UI Menus
    Given the user navigates to "JQuery UI Menus"
    When the user hovers over "Enabled"
    And the user hovers over "Downloads"
    And the user clicks extension "PDF"
    Then a file named "menu.pdf" should download

  @js @key_presses
  Scenario: Key Presses logging
    Given the user navigates to "Key Presses"
    When the user presses the "ENTER" key
    Then the result text should say "You entered: ENTER"
