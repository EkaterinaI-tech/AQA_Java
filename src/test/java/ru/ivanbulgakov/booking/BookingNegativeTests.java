package ru.ivanbulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.ivanbulgakov.booking.dto.AuthRequest;
import ru.ivanbulgakov.booking.dto.CreateBookingDTO;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingNegativeTests {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @ParameterizedTest(name = "Сценарий: {1}")
    @MethodSource("authDataProvider")
    void authNegativeTest(AuthRequest requestBody, String description) {
        Response resp = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                //.when()
                .post(BASE_URL + "/auth")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.jsonPath().getString("token")).isNull();
    }

    private static Stream<Arguments> authDataProvider() {
        return Stream.of(
                Arguments.of(new AuthRequest("admin", "wrong123"), "Неверный пароль"),
                Arguments.of(new AuthRequest("unknown", "password123"), "Неверный логин"),
                Arguments.of(new AuthRequest("admin", ""), "Пустой пароль"),
                Arguments.of(new AuthRequest("", "password123"), "Пустой логин"),
                Arguments.of(new AuthRequest(null, null), "Пустое тело запроса")
        );
    }

    @Test
    @DisplayName("Авторизация без передачи body вообще")
    void authWithoutBodyTest() {
        Response resp = given()
                .contentType(ContentType.JSON)
                //without body()
                //.when()
                .post(BASE_URL + "/auth")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.jsonPath().getString("token")).isNull();
    }

    private static Stream<Arguments> bookingDataProvider() {
        return Stream.of(
                Arguments.of(CreateBookingDTO.builder()
                        .lastname("Ivanov").totalprice(100).depositpaid(true)
                        .bookingdates(CreateBookingDTO.BookingDates.builder()
                                .checkin("2026-01-01")
                                .checkout("2026-01-02")
                                .build())
                        .build(), "Запрос без firstname", 500),

                Arguments.of(CreateBookingDTO.builder()
                        .firstname("Ivan").totalprice(100).depositpaid(true)
                        .bookingdates(CreateBookingDTO.BookingDates.builder()
                                .checkin("2026-01-01")
                                .checkout("2026-01-02")
                                .build())
                        .build(), "Запрос без lastname", 500),

                Arguments.of(CreateBookingDTO.builder()
                        .firstname("Ivan").lastname("Ivanov").totalprice(-500).depositpaid(true)
                        .bookingdates(CreateBookingDTO.BookingDates.builder()
                                .checkin("2026-01-01")
                                .checkout("2026-01-02")
                                .build())
                        .build(), "Отрицательная цена", 200),

                Arguments.of(CreateBookingDTO.builder()
                        .firstname("Ivan").lastname("Ivanov").totalprice(100).depositpaid(true)
                        .bookingdates(CreateBookingDTO.BookingDates.builder()
                                .checkin("не-дата")
                                .checkout("2026-01-02")
                                .build())
                        .build(), "Неверный формат даты", 200),

                Arguments.of(CreateBookingDTO.builder()
                        .firstname("Ivan").lastname("Ivanov").totalprice(100).depositpaid(true)
                        .bookingdates(CreateBookingDTO.BookingDates.builder()
                                .checkin("2026-01-10")
                                .checkout("2026-01-01")
                                .build())
                        .build(), "Выезд раньше заезда", 200),

                Arguments.of(CreateBookingDTO.builder().build(), "Пустое тело запроса {}", 500)
        );
    }

    @ParameterizedTest(name = "Сценарий: {1}")
    @MethodSource("bookingDataProvider")
    void createBookingNegativeTest(CreateBookingDTO requestBody, String description, int expectedStatus) {
        Response resp = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(BASE_URL + "/booking")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(expectedStatus);
        if (resp.statusCode() == 200) {
            assertThat(resp.jsonPath().getString("bookingid")).isNotNull();
        }
    }
}

/*
В «здоровом» и правильно написанном API на ошибки должны приходить такие коды:
401 Unauthorized — если логин или пароль неверные (самый правильный код для этого случая).
400 Bad Request — если ты прислала пустой JSON или вообще забыла тело запроса.
403 Forbidden — если ты залогинилась, но пытаешься сделать то, на что у тебя нет прав (например, обычный юзер лезет в админку).
 */