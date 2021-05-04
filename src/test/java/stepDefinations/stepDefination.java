package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleMapAPI;
import pojo.location;

public class stepDefination extends Utils {
    
    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    static String place_id ;
    TestDataBuild data=new TestDataBuild();
    APIResources resourceAPI;
    @Given("Add Place Payload with {string} {string}")
    public void add_Place_Payload_with(String name,String address) throws IOException  {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
			    
	     
	   res = given().spec(requestSpecification()).body(data.addPlacePayload(name,address));
	}

    @When("user calls {string} API with {string} http request")
    public void user_calls_API_with_http_request(String resource, String method) {
    	//constructor will be called with value of resource which you pass
	    resourceAPI = APIResources.valueOf(resource);
	    System.out.println(	resourceAPI.getResource());
	
		resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		{
			response = res.when().post(resourceAPI.getResource());
		}
		if(method.equalsIgnoreCase("GET"))
		{
			response = res.when().get(resourceAPI.getResource());
		}
	 
	}

	@Then("the API call is success wit status code {int}")
	public void the_API_call_is_success_wit_status_code(Integer int1) {
     assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String keyValue, String expectedKeyValue) {
	   
	    
	    assertEquals(getJsonPath(response,keyValue), expectedKeyValue);
	    
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
	 //prepare request spec
		place_id = getJsonPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_API_with_http_request( resource, "GET");
		String name = getJsonPath(response,"name");
		assertEquals(expName,name);
		System.out.println("Name mapped");
	}

	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	   res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	   
	  
	}
}
