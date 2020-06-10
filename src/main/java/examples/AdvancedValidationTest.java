package examples;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AdvancedValidationTest {

    @Before
    public void printJson(){
        System.out.println(get("https://restapi.wcaquino.me/users").asString());
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
        String temp = given()
                .when().get("https://restapi.wcaquino.me/users")
                .path("findAll{it.age <= 25}").toString();
        System.out.println(temp);
    }
}
