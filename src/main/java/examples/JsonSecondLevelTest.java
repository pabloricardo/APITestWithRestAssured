package examples;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


public class JsonSecondLevelTest {

    @Test
    public void jsonSecondLevel(){
        Response resp = RestAssured.given().when().get("https://restapi.wcaquino.me/users/2");

        System.out.println(resp.body().asString());
        System.out.println(resp.path("endereco.rua").toString());

        JsonPath jsPath = new JsonPath(resp.asString());
        System.out.println(jsPath.get("endereco.numero").toString());
        
    }
}
