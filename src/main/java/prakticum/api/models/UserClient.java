package prakticum.api.models;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UserClient {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String USER_ENDPOINT = "/api/auth/user";

    @Step("Создание пользователя")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    @Step("Авторизация пользователя")
    public Response login(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .baseUri(BASE_URI)
                .when()
                .delete(USER_ENDPOINT);
    }

    public String getAccessToken(Response response) {
        return response.then().extract().path("accessToken");
    }

}

