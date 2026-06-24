package ru.ivanbulgakov.booking.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.jspecify.annotations.NonNull;
import ru.ivanbulgakov.booking.BookingApiClient;
import ru.ivanbulgakov.dto.BookingDTO;
import ru.ivanbulgakov.dto.BookingId;
import ru.ivanbulgakov.dto.CreateBookingResponse;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BookingSteps {
    private static final Faker faker = new Faker();

    private final BookingApiClient bookingApiClient = new BookingApiClient();

    public CreateBookingResponse createBooking() {
    return createBooking(randomBooking());
}

    public CreateBookingResponse createBooking(BookingDTO booking) {
        Response createResp = bookingApiClient.createBooking(booking);
        step("Проверить, что статус-код = 200", () ->
                assertThat(createResp.getStatusCode()).isEqualTo(200));

        return createResp.as(CreateBookingResponse.class);
    }

    @Step("Сгенерировать {bookingQuantity} бронирований с фамилией {lastName}")
    public @NonNull List<Integer> generateBookings(int bookingQuantity, String lastName) {
        List<Integer> bookingIds = new ArrayList<>();
        for (int i = 0; i < bookingQuantity; i++) {
            BookingDTO bookingDTO = randomBooking();
            bookingDTO.setLastname(lastName);
            Integer bookingId = createBooking(bookingDTO).getBookingid();
            bookingIds.add(bookingId);
        }
        return bookingIds;
    }

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

    @Step("Проверить список бронирований")
    public static void bookingListShouldBeValid(List<BookingId> bookings, List<Integer> expectedBookingIds, int expectedQuantity) {
        assertThat(bookings)
                .as("Количество бронирований")
                .hasSize(expectedQuantity)
                .as("Список бронирований не должен содержать null")
                .doesNotContainNull()
                .as("Список бронирований не должен содержать дубликаты")
                .doesNotHaveDuplicates()
                .extracting(booking -> booking.bookingid())
                .as("ID бронирований должны совпадать с ожидаемыми")
                .containsExactlyInAnyOrderElementsOf(expectedBookingIds);
    }

    public static BookingDTO randomBooking() {
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