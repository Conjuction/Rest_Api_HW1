import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    public static final String BASE_URL = "https://reqres.in/api";
    @Test
    void checkIdTest() {

        given()
                .log().uri()
                .when()
                .get(BASE_URL + "/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", hasItems(1,2,3,4,5,6));;
    }

    @Test
    void checkIdNameTest() {

        given()
                .log().uri()
                .when()
                .get(BASE_URL + "/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.name", is ("fuchsia rose"));
    }

    @Test
    void createUserTest() {
        String user = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
                .when()
                .post(BASE_URL + "/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is ("morpheus"))
                .body("job", is ("leader"));
    }

    @Test
    void updateUserTest() {
        String user = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
                .when()
                .put(BASE_URL + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is ("morpheus"))
                .body("job", is ("zion resident"));
    }

    @Test
    void registerUserTest() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post(BASE_URL + "/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is (4))
                .body("token", is ("QpwL5tke4Pnpja7X4"));
    }
}
