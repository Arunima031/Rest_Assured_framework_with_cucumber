package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;



import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    RequestSpecification payloadRes;
    Response APIResponse;
    TestDataBuild testData= new TestDataBuild();
    static String place_id;

    @Given("Add place payload with {string} and {string}")
    public void add_place_payload(String name, String language) throws IOException {
        payloadRes = given().spec(requestSpecification())
                .body(testData.addPlacePayload(name,language));
    }
    @When("User hits {string} request with {string} request")
    public void user_hits_request(String apiName,String method) {
        APIResource resourceAPI = APIResource.valueOf(apiName);
        if(method.equalsIgnoreCase("POST")){
            APIResponse =
                    payloadRes.when().post(resourceAPI.getResource());
        }
        else if(method.equalsIgnoreCase("GET")){
            APIResponse =
                    payloadRes.when().get(resourceAPI.getResource());
        }
        else if(method.equalsIgnoreCase("Delete")){
            APIResponse =
                    payloadRes.when().delete(resourceAPI.getResource());
        }

    }
    @Then("The API is success with status code {int}")
    public void the_api_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(APIResponse.then().spec(responseSpecification()).extract().response().getStatusCode(),200);

    }
    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String Actvalue = getJsonPath(APIResponse, key);
        Assert.assertEquals(Actvalue,value);
    }
    @And("Verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String apiName) throws IOException {
        place_id=getJsonPath(APIResponse,"place_id");
        System.out.println(place_id);
        payloadRes =given().log().all().spec(requestSpecification()).
               queryParam("place_id",place_id);
        user_hits_request(apiName,"GET");
        String ActName = getJsonPath(APIResponse,"name");
        Assert.assertEquals(ActName,name);
    }
    @Given("Delete payload")
    public void delete_payload() throws IOException {
        payloadRes =given().log().all().spec(requestSpecification()).
               body(testData.deletePlacePayload(place_id));
    }
}
