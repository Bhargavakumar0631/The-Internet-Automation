Feature: Standard UI Elements
  As a user, I want to interact with common HTML components
  So that I can ensure standard inputs and tables work correctly

  Background:
    Given the user navigates to the homepage

  @ui @add_remove_elements
  Scenario: Add and Remove Elements
    Given the user navigates to "Add/Remove Elements"
    When the user clicks Add Element 3 times
    Then 3 Delete buttons should be visible
    When the user clicks a Delete button
    Then 2 Delete buttons should remain visible

  @ui @checkboxes
  Scenario: Toggling Checkboxes
    Given the user navigates to "Checkboxes"
    When the user clicks checkbox "checkbox 1"
    Then "checkbox 1" should be checked
    When the user clicks checkbox "checkbox 2"
    Then "checkbox 2" should be unchecked

  @ui @dropdown
  Scenario Outline: Selecting options from a Dropdown
    Given the user navigates to "Dropdown"
    When the user selects "<option_name>" from the dropdown
    Then the dropdown value should be "<option_value>"

    Examples:
      | option_name | option_value |
      | Option 1    | 1            |
      | Option 2    | 2            |

  @ui @inputs
  Scenario: Interacting with Number Inputs
    Given the user navigates to "Inputs"
    When the user types "10" into the input field
    And presses the up arrow key
    Then the input value should be "11"

  @ui @horizontal_slider
  Scenario: Horizontal Slider interaction
    Given the user navigates to "Horizontal Slider"
    When the user moves the slider to "3.5"
    Then the slider value text should display "3.5"

  @ui @sortable_data_tables
  Scenario: Sortable Data Tables
    Given the user navigates to "Sortable Data Tables"
    When the user clicks the "Last Name" column header in Example 1
    Then the table rows should be sorted alphabetically by Last Name
