Feature: Miscellaneous Web Scenarios
  As a user, I want to test edge cases and specific testing endpoints
  So that I can verify all remaining demo functionalities

  Background:
    Given the user navigates to the homepage

  @misc @ab_testing
  Scenario: A/B Testing variation display
    When the user navigates to "A/B Testing"
    Then the header should display either "A/B Test Variation 1" or "A/B Test Control"

  @misc @typos
  Scenario: Identifying Typos
    When the user navigates to "Typos"
    Then the paragraph text should contain known spelling variations "won't" vs "won,t"

  @misc @wysiwyg_editor
  Scenario: WYSIWYG Editor interaction
    Given the user navigates to "WYSIWYG Editor"
    When the WYSIWYG Editor is editable
    And the user clears the default text
    And the user types "Hello Automation"
    And the user clicks the "Bold" format button
    Then the text inside the editor should be wrapped in strong tags
