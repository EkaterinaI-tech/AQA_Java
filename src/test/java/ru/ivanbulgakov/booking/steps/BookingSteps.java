package ru.ivanbulgakov.booking.steps;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import ru.ivanbulgakov.dto.BookingDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BookingSteps {
    private static final Faker faker = new Faker();

    @Step("Проверить соответствие всех полей в ответе")
    public static void bookingsShouldBeEqual(BookingDTO expected, BookingDTO actual) {
        assertAll(
                () -> assertThat(expected.getFirstname())
                        .as("firstName отличается от ожидаемого")
                        .isEqualTo(actual.getFirstname()),
                () -> assertThat(expected.getLastname())
                        .as("lastName отличается от ожидаемого")
                        .isEqualTo(actual.getLastname()),
                () -> assertThat(expected.getTotalprice())
                        .as("totalPrice отличается от ожидаемого")
                        .isEqualTo(actual.getTotalprice()),
                () -> assertThat(expected.getDepositpaid())
                        .as("depositpaid отличается от ожидаемого")
                        .isEqualTo(actual.getDepositpaid()),
                () -> assertThat(expected.getAdditionalneeds())
                        .as("additionalneeds отличается от ожидаемого")
                        .isEqualTo(actual.getAdditionalneeds()),
                () -> assertThat(expected.getBookingdates())
                        .as("bookingdates равен null")
                        .isNotNull(),
                () -> assertThat(expected.getBookingdates().getCheckin())
                        .as("checkin отличается от ожидаемого")
                        .isEqualTo(actual.getBookingdates().getCheckin()),
                () -> assertThat(expected.getBookingdates().getCheckout())
                        .as("checkout отличается от ожидаемого")
                        .isEqualTo(actual.getBookingdates().getCheckout())
        );
    }

    public static BookingDTO buildBookingRequest() {
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