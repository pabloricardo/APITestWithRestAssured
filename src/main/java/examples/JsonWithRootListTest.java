package examples;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonWithRootListTest {

    public static Response resp;

    @Before
    public void printJson() {
        resp = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users");
        System.out.println(resp.asString());
    }

    @Test
    public void printJsonPath(){
        System.out.println(resp.path("filhos[2]").toString());
        JsonPath jsonPath = new JsonPath(resp.asString());
        System.out.println(jsonPath.get("filhos[2].name[1]").toString());
        System.out.println(JsonPath.from(resp.asString()).getString("filhos[2].name[0]"));
    }

    @Test
    public void jsonWithList(){
        given()
        .when()
            .get("https://restapi.wcaquino.me/users")
        .then()
            .body("", hasSize(3))
            .body("filhos[2].name[0]", is("Zezinho"));
    }
}
