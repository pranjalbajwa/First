package Resources;

import java.util.ArrayList;

import pojo.GoogleMapAPI;
import pojo.location;

public class TestDataBuild {
public GoogleMapAPI addPlacePayload(String name,String address){
	GoogleMapAPI GMA = new GoogleMapAPI();
	GMA.setAccuracy(10);
	GMA.setAddress(address);
	GMA.setName(name);
	location loc=new location();
	loc.setLat(123344);
	loc.setLng(223233);
    GMA.setLocation(loc);
   ArrayList<String> newList = new ArrayList<String>();
   newList.add("shoe park");
   newList.add("Shop");
   GMA.setTypes(newList);
return GMA;
}
public static String deletePlacePayload(String placeid)
{
	return "{\"place_id\":\""+placeid+"\"}";
}
}
