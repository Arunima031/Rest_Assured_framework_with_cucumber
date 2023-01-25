package resources;

import pojoClassess.Location;
import pojoClassess.locationAPI;

import java.util.Arrays;
import java.util.List;

public class TestDataBuild {
    public locationAPI addPlacePayload(String name, String language){
        locationAPI lp =new locationAPI();
        lp.setAccuracy(50);
        lp.setAddress("29, side layout, cohen 0");
        lp.setLanguage(language);
        lp.setPhone_number("(+91) 983 893 3937");
        lp.setWebsite("http://google.com");
        lp.setName( name);
        Location nl = new Location();
        nl.setLat(-38.383494);
        nl.setLng(33.427362);
        lp.setLocation(nl);
        List<String> s= Arrays.asList("shoe park","shop");
        lp.setTypes(s);
        return lp;
    }
    public String deletePlacePayload(String place_id){
        return "{\n" +
                "\"place_id\": \""+place_id+"\"\n" +
                "}";

    }
}
