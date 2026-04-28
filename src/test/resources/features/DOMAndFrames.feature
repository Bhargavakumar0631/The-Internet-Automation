Feature: DOM Manipulation and Frames
  As a user, I want to interact with nested HTML structures and complex DOMs
  So that I can ensure the framework handles context switching accurately

  Background:
    Given the user navigates to the homepage

  @dom @frames @nested_frames
  Scenario: Switching context in Nested Frames
    Given the user navigates to "Frames"
    And clicks "Nested Frames"
    When the user switches to the "top" frame, then the "middle" frame
    Then the body text should be "MIDDLE"

  @dom @frames @iframe
  Scenario: Interacting with standard iFrame
    Given the user navigates to "Frames"
    And clicks "iFrame"
    When the user switches to the iframe context
    Then the text editor should be interactable

  @dom @challenging_dom
  Scenario: Challenging DOM with dynamic IDs
    Given the user navigates to "Challenging DOM"
    When the user clicks the blue button
    Then the IDs of all three buttons should change
    And the canvas element should render a different numeric value

  @dom @large_deep_dom
  Scenario: Navigating Large & Deep DOM
    Given the user navigates to "Large & Deep DOM"
    When the user queries the DOM for sibling element "50.3"
    Then the element should exist and contain the text "50.3"

  @dom @shadow_dom
  Scenario: Accessing Shadow DOM elements
    Given the user navigates to "Shadow DOM"
    When the user inspects the shadow host
    Then the text inside the shadow root should be accessible and verifiable

  @dom @shifting_content
  Scenario: Shifting Content on reload
    Given the user navigates to "Shifting Content"
    And clicks on "Menu Element" and checks pixel Location of "Gallery"
    When the user refreshes the page to check changed position
    Then the pixel location of the "Gallery" menu item should shift
