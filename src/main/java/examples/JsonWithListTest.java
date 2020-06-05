package examples;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonWithListTest {

    @Before
    public void printJson(){
        Response resp = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/3");
        System.out.println(resp.asString());
    }


    @Test
    public void jsonWithList(){
        given()
        .when()
            .get("https://restapi.wcaquino.me/users/3")
        .then()
            .body("filhos", hasSize(2))
            .body("filhos[0].name", is("Zezinho"))
            .body("filhos.name", hasItems("Luizinho", "Zezinho"));
    }
}
