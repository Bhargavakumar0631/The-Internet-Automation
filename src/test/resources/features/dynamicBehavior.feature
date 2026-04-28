@dynamicBehavior
Feature: Dynamic Web Elements
  As a user, I want to interact with elements that change state dynamically
  So that I can verify asynchronous and dynamic rendering logic

  Background:
    Given the user navigates to the homepage

  @dynamic @dynamic_content
  Scenario: Dynamic Content changes on reload
    Given the user navigates to "Dynamic Content"
    When the user refreshes the page to see the change
    Then the text and images in the content blocks should change

  @dynamic @dynamic_controls
  Scenario: Dynamic Controls - Checkbox and Input
    Given the user navigates to "Dynamic Controls"
    When the user clicks the Remove button
    Then the loading indicator appears and the message "It's gone!" is displayed and checkbox has disappeared
    When the user clicks the Enable button
    Then the loading indicator appears with text "It's enabled!" and the input field becomes enabled

  @dynamic @dynamic_loading
  Scenario Outline: Dynamic Loading of hidden and rendered elements
    Given the user navigates to "Dynamic Loading"
    When the user clicks the link for Example "<example_number>"
    And the user clicks the Start button
    Then the loading bar appears and disappears
    And the text "Hello World!" is displayed

    Examples:
      | example_number | description                      |
      | 1              | Element is hidden in DOM         |
      | 2              | Element is rendered after loading|

  @dynamic @disappearing_elements
  Scenario: Disappearing Elements
    Given the user navigates to "Disappearing Elements"
    When the user refreshes the page multiple times until state of "Gallery" button changes
    Then the "Gallery" button should randomly appear and disappear from the DOM
