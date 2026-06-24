package ru.ivanbulgakov.booking;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.ivanbulgakov.booking.config.BookingConfig;
import ru.ivanbulgakov.dto.AuthRequest;
import ru.ivanbulgakov.dto.AuthResponse;
import ru.ivanbulgakov.dto.BookingDTO;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.ivanbulgakov.booking.config.BookingApiConfig.getBookingConfig;

public class BookingApiClient {
    private static final BookingConfig CFG = getBookingConfig();

    private RequestSpecification spec = new RequestSpecBuilder()
            .addRequestSpecification(RestAssured.requestSpecification)
            .setBaseUri(CFG.bookingUrl())
            .setContentType(ContentType.JSON)
            .build();

    @Step("Выполнить запрос POST /auth")
    public Response auth(String user, String password) {
        return given(spec)
                .body(new AuthRequest(user, password))
                .post("/auth")
                .then()
                .log().status()
                .extract().response();
    }

    @Step("Выполнить запрос GET /booking/{id}")
    public Response getBooking(Integer id) {
        return given(spec)
                .pathParam("BOOKING_ID", id)
                .get("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    @Step("Выполнить запрос GET /booking")
    public Response getBookings(Map<String, Object> queryParams) {
        return given(spec)
                .queryParams(queryParams)
                .log().params()
                .get("/booking")
                .then()
                .extract().response();
    }

    @Step("Выполнить запрос POST /booking")
    public Response createBooking(BookingDTO bookingDTO) {
        return given(spec)
                .body(bookingDTO)
                .post("/booking")
                .then()
                .extract().response();
    }

    @Step("Выполнить запрос PUT /booking/{id}")
    public Response updateBooking(BookingDTO bookingDTO, Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .put("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    @Step("Выполнить запрос PATCH /booking/{id}")
    public Response partialUpdateBooking(BookingDTO bookingDTO, Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .patch("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    @Step("Выполнить запрос DELETE /booking/{id}")
    public Response deleteBooking(Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .pathParam("BOOKING_ID", id)
                .delete("/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    private String getToken() {
        return auth(CFG.username(), CFG.password()).as(AuthResponse.class).getToken();
    }
}
