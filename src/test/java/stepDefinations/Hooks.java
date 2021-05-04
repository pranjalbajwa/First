package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
@Before("@deletePlace")
public void beforeScenario() throws IOException
{
	
	stepDefination m=new stepDefination();
	if(m.place_id==null)
	{
	m.add_Place_Payload_with("Rahul", "JatLand");
	m.user_calls_API_with_http_request("AddPlaceAPI", "POST");
	m.verify_place_id_created_maps_to_using("Rahul", "getPlaceAPI");
	}
}
}
