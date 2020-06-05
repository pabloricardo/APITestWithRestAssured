package examples;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExampleMatchersHamcrestTest {

    @Test
    public void matchersHamcrest(){
        assertThat("Maria", Matchers.is("Maria"));
        assertThat(128, Matchers.isA(Integer.class));
        assertThat(128d, Matchers.isA(Double.class));
        assertThat(128d, Matchers.greaterThan(120d));
        assertThat(128d, Matchers.lessThan(130d));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1,3,5,7,9));
        assertThat(impares, containsInAnyOrder(1,3,5,9,7));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(1,9));

        assertThat("Maria", not("Pedro"));
        assertThat("Maria", anyOf(is("Maria"), is("Joana")));
        assertThat("Maria", allOf(startsWith("Ma"), endsWith("ia"), containsString("ria")));

        List<String> str = new ArrayList<>();
        assertThat(str, empty());
    }

    @Test
    public void validateBody(){
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200)
            .body(is(notNullValue()))
            .body(is("Ola Mundo!"));
    }
}
