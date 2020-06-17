package examples;


import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AdvancedValidationTest {

    @Before
    public void printJson(){
        //System.out.println(get("https://restapi.wcaquino.me/users").asString());
    }

    @Test
    public void advancedValidation(){
        given()
        .when().get("https://restapi.wcaquino.me/users")
        .then().body("findAll{it.age <= 25}.size()", is(2))
                .body("findAll{it.salary < 1300 && it.salary != null}.name.toArray()", allOf(arrayContaining("João da Silva"), arrayWithSize(1)));
                //.body("findAll{it.name == 'Ana Júlia'}.filhos.size()", hasSize(2));
    }


    @Test
    public void advancedValidationJsonPath(){
        ArrayList<String> names = given()
                .when().get("https://restapi.wcaquino.me/users")
                .then().statusCode(200)
                .extract().path("findAll{it.age <= 25}.name");
        System.out.println(names.get(0));
    }

    @Test
    public void test(){
        String resp = given()
                .when().get("https://restapi.wcaquino.me/users")
                .then().statusCode(200)
                .extract().path("findAll{it.age <= 25}");
        JsonPath jsonPath = new JsonPath(resp);
        System.out.println(jsonPath.toString());

    }
}
