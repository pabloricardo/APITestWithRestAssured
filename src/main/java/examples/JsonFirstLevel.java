package examples;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonFirstLevel {

    @Before
    public void printJson(){
        System.out.println(given().when().get("https://restapi.wcaquino.me/users/1").asString());
    }

    @Test
    public void assertJsonFirstLevel(){
        given()
        .when()
            .get("https://restapi.wcaquino.me/users/1")
        .then()
            .statusCode(200)
            .body("id", is(1))
            .body("name", is(containsString("Silva")))
            .body("age", greaterThan(18));
    }

    @Test
    public void getJsonFirstLevel(){
        Response resp = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");

        //path
        Assert.assertEquals( new Integer(1), resp.path("id"));
        System.out.println(resp.path("id").toString());

        //jsonpath
        JsonPath jpath = new JsonPath(resp.asString());
        Assert.assertEquals(1, jpath.getInt("id"));

        //from
        int id = JsonPath.from(resp.asString()).getInt("id");
        Assert.assertEquals(1, id);
    }
}
