package API.clients;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class ClientUser {

    private static final String USER_AUTH_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String USER_LOGIN_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/login";

    @Step("deleteUser")
    public void deleteUser(String accessToken) {
         given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(USER_AUTH_ENDPOINT);
    }

    @Step("loginUserReturnToken")
    public String loginUserReturnToken(String loginUser) {
        return given()
                .header("Content-type", "application/json")
                .body(loginUser) //передаем инфу по курьеру
                .when()
                .post(USER_LOGIN_ENDPOINT)
                .then()
                .extract().path("accessToken");
    }
}
