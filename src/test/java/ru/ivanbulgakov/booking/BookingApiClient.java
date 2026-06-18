package ru.ivanbulgakov.booking;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.ivanbulgakov.booking.config.BookingConfig;
import ru.ivanbulgakov.dto.AuthRequest;
import ru.ivanbulgakov.dto.AuthResponse;
import ru.ivanbulgakov.dto.BookingDTO;

import static io.restassured.RestAssured.given;
import static ru.ivanbulgakov.booking.config.BookingApiConfig.getBookingConfig;

public class BookingApiClient {
    private static final BookingConfig CFG = getBookingConfig();

    private RequestSpecification spec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();

    public Response auth(String user, String password) {
        return given(spec)
                .body(new AuthRequest(user, password))
                .post(CFG.bookingUrl() + "/auth")
                .then()
                .extract().response();
    }

    public Response getBooking(Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .pathParam("BOOKING_ID", id)
                .get(CFG.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    public Response createBooking(BookingDTO bookingDTO) {
        return given(spec)
                .body(bookingDTO)
                .post(CFG.bookingUrl() + "/booking")
                .then()
                .extract().response();
    }

    public Response updateBooking(BookingDTO bookingDTO, Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .put(CFG.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    public Response partialUpdateBooking(BookingDTO bookingDTO, Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .body(bookingDTO)
                .pathParam("BOOKING_ID", id)
                .patch(CFG.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    public Response deleteBooking(Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .pathParam("BOOKING_ID", id)
                .delete(CFG.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    private String getToken() {
        return auth(CFG.username(), CFG.password()).as(AuthResponse.class).getToken();
    }
}
