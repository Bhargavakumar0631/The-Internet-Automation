Feature: Page Events and Navigations
  As a user, I want to trigger events based on page scrolling and mouse position
  So that I can verify event listeners are functioning

  Background:
    Given the user navigates to the homepage

  @nav @entry_ad
  Scenario: Closing an Entry Ad modal
    Given the user navigates to "Entry Ad"
    When the modal window appears
    And the user clicks Close
    Then the modal window should disappear

  @nav @exit_intent
  Scenario: Triggering Exit Intent modal
    Given the user navigates to "Exit Intent"
    When the user moves the mouse cursor outside the browser viewport
    Then the exit intent modal should appear

  @nav @floating_menu
  Scenario: Floating Menu visibility on scroll
    Given the user navigates to "Floating Menu"
    When the user scrolls to the bottom of the page
    Then the floating menu should remain visible in the viewport

  @nav @infinite_scroll
  Scenario: Infinite Scroll loading new elements
    Given the user navigates to "Infinite Scroll"
    When the user scrolls to the bottom of the page
    Then new DOM paragraphs should be appended to the page dynamically

  @nav @notification_messages
  Scenario: Notification Messages variance
    Given the user navigates to "Notification Messages"
    When the user clicks "Click here" multiple times
    Then the flash message should toggle between "Action successful" and "Action unsuccesful"

  @nav @geolocation
  Scenario: Geolocation mocking
    Given the user navigates to "Geolocation"
    When the user grants location permissions
    And clicks the "Where am I?" button
    Then the simulated latitude and longitude coordinates should be displayed
