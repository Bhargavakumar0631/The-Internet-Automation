 Feature: Advanced UI Interactions
  As a user, I want to perform complex mouse and window interactions
  So that I can verify advanced browser capabilities

  Background:
    Given the user navigates to the homepage

  @advanced @drag_and_drop
  Scenario: Drag and Drop elements
    Given the user navigates to "Drag and Drop"
    When the user drags column "A" over column "B"
    Then the headers of the columns should swap places

  @advanced @hovers
  Scenario: Mouse Hovers revealing hidden content
    Given the user navigates to "Hovers"
    When the user hovers over the first avatar
    And the text "name: user1" and a "View profile" link should appear
    Then the users should click on "View profile" link and next page with heading Not found

  @advanced @context_menu
  Scenario: Context Menu triggered by right-click
    Given the user navigates to "Context Menu"
    When the user right-clicks on the designated hot spot
    Then a JavaScript alert saying "You selected a context menu" should appear
    And the user accepts the alert

  @advanced @multiple_windows
  Scenario: Multiple Windows handling
    Given the user navigates to "Multiple Windows"
    When the user clicks link "Click Here"
    Then a new window opens
    When the user switches focus to the new window
    Then the page text should be "New Window"
