Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is successfully added using Add Place API's
Given Add Place Payload with "<name>" "<address>"
When user calls "AddPlaceAPI" API with "Post" http request
Then the API call is success wit status code 200
And "status" in response is "OK"
And "scope" in response is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"
Examples:
 |name   |  address   |
 |raj    | karamchari |
#|Pranjal|adarsh nagar|

@deletePlace
Scenario: Verify if Delete Place functionality is working
Given DeletePlace payload
When user calls "deletePlaceAPi" API with "Post" http request
Then the API call is success wit status code 200
