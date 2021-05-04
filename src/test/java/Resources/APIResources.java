package Resources;

public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPi("/maps/api/place/delete/json"),
	getPlaceAPI("/maps/api/place/get/json");
	private String Resource;
	
	APIResources(String Resource)
	{
		this.Resource=Resource;
	}
	public String getResource()
	{
		return Resource;
	}
	
}
