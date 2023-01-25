package stepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition s= new StepDefinition();
        if(s.place_id==null){
            s.add_place_payload("Fransis","English");
            s.user_hits_request("AddPlaceApi","POST");
            s.verify_place_id_created_maps_to_using("Fransis","GetPlaceApi");
        }

    }

}
