package resources;

public enum APIResource {

    AddPlaceApi("/maps/api/place/add/json"),
    GetPlaceApi("/maps/api/place/get/json"),
    DeletePlaceApi("/maps/api/place/delete/json");
    private String resource;
    APIResource(String resource) {
        this.resource=resource;
    }


    public String getResource() {
        return resource;
    }
}
