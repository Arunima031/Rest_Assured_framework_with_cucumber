package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {
        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
           req = new RequestSpecBuilder().setBaseUri(globalValue("baseURl")).addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log)).
                    build();
            return req;
        }
        return  req;
    }
    public ResponseSpecification responseSpecification(){
        ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
    expectStatusCode(200).build();
        return res;

    }
    public static String globalValue(String property) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        String propValue = prop.getProperty(property);
        return propValue;
    }
    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js=new JsonPath(resp);
        String getValue = js.getString(key);
        return getValue;
    }
}
