Feature: Validating add place API's
 @AddPlace @Regression
  Scenario Outline: Verify adding place via API

    Given Add place payload with "<name>" and "<language>"
    When User hits "AddPlaceApi" request with "POST" request
    Then The API is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_id created maps to "<name>" using "GetPlaceApi"

    Examples:
      | name           | language |
      | Frontline house | French   |
#      | Backstreet     | Japanese |

  @DeletePlace @Regression
  Scenario: Verify if delete place api is working
     Given Delete payload
     When User hits "DeletePlaceApi" request with "POST" request
     Then The API is success with status code 200
     And "status" in response body is "OK"



