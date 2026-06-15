package ru.ivanbulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.dto.AuthRequest;
import ru.ivanbulgakov.dto.AuthResponse;
import ru.ivanbulgakov.dto.BookingDTO;
import ru.ivanbulgakov.dto.CreateBookingResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingTest {
    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";
    private static final Faker faker = new Faker();
    private static final String USER = "admin", PASSWORD = "password123";

    private final BookingApiClient bookingApiClient = new BookingApiClient();

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void authTest() {

        Response resp = bookingApiClient.auth(USER, PASSWORD);

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    void createBookingTest() {
        Response resp = bookingApiClient.createBooking(buildBookingRequest());

        assertThat(resp.getStatusCode()).isEqualTo(200);

        CreateBookingResponse createBookingResponse = resp.as(CreateBookingResponse.class);
        assertThat(createBookingResponse.getBookingid()).isNotNull();
        assertThat(createBookingResponse.getBooking().getTotalprice()).isEqualTo(1000);
        assertThat(createBookingResponse.getBooking().getBookingdates().getCheckin()).isEqualTo("2026-01-01");
        assertThat(createBookingResponse.getBooking().getDepositpaid()).isFalse();
    }

    @Test
    void updateBookingTest() {
        // авторизацию -> создать букинг -> его обновить

        String token = bookingApiClient.auth(USER, PASSWORD)
                .as(AuthResponse.class)
                .getToken();

        Response createResp = bookingApiClient.createBooking(buildBookingRequest());
        assertThat(createResp.getStatusCode()).isEqualTo(200);

        BookingDTO bookingDTO = buildBookingRequest();

        Response updateResponse = bookingApiClient
                .updateBooking(bookingDTO, createResp.as(CreateBookingResponse.class).getBookingid());
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDTO updatedBookingDTO = updateResponse.as(BookingDTO.class);
        assertThat(updatedBookingDTO.equals(bookingDTO)).isTrue();
    }

/*
    private static CreateBookingDTO bookingRequest() {
        CreateBookingDTO booking = new CreateBookingDTO();
        booking.setFirstname("Barack");
        booking.setLastname("Obama");
        booking.setTotalprice(1000);
        booking.setDepositpaid(false);
        booking.setBookingdates(new CreateBookingDTO.BookingDates("2026-01-01", "2027-01-01"));
        booking.setAdditionalneeds("newspaper");

        return booking;
    }
 */

    private static BookingDTO buildBookingRequest() {
        return BookingDTO.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(1000, 10000))
                .depositpaid(faker.bool().bool())
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2026-01-01")
                        .checkout("2027-01-01")
                        .build())
                .additionalneeds(faker.videoGame().title())
                .build();
    }
}