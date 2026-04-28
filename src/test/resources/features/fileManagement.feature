Feature: File Uploads and Downloads
  As a user, I want to transfer files to and from the server
  So that I can verify file management endpoints

  Background:
    Given the user navigates to the homepage

  @file @file_download
  Scenario Outline: File Download
    Given the user navigates to "File Download"
    When the user clicks on a file link ending with extension "<extensionName>"
    Then the file should be successfully downloaded to the local directory

    Examples:
      | extensionName |
      | .jpg |
      | .xlsx |
      | .pdf |

  @file @file_upload
  Scenario: File Upload
    Given the user navigates to "File Upload"
    When the user selects the file "test-image.png"
    And clicks the Upload button
    Then the "File Uploaded!" success message should appear
