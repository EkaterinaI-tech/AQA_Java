package ru.ivanbulgakov.booking;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.booking.config.BookingConfig;
import ru.ivanbulgakov.booking.steps.BookingSteps;
import ru.ivanbulgakov.dto.AuthResponse;
import ru.ivanbulgakov.dto.BookingDTO;
import ru.ivanbulgakov.dto.CreateBookingResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ivanbulgakov.booking.config.BookingApiConfig.getBookingConfig;
import static ru.ivanbulgakov.booking.steps.BookingSteps.buildBookingRequest;

public class BookingTest extends BaseApiTest {
    private static final Faker faker = new Faker();
    private static final BookingConfig CFG = getBookingConfig();

    private final BookingApiClient bookingApiClient = new BookingApiClient();

    @Test
    @DisplayName("Авторизация: успешное получение токена")
    @Tag("API")
    @Tag("SMOKE")
    @Tag("AUTH")
    @Severity(SeverityLevel.BLOCKER)
    void authTest() {

        Response resp = bookingApiClient.auth(CFG.username(), CFG.password());

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    @DisplayName("Бронирование: создание новой записи")
    @Tag("API")
    @Tag("SMOKE")
    @Tag("BOOKING")
    @Severity(SeverityLevel.CRITICAL)
    void createBookingTest() {
        BookingDTO bookingDTO = buildBookingRequest();
        Response resp = bookingApiClient.createBooking(bookingDTO);

        assertThat(resp.getStatusCode()).isEqualTo(200);

        CreateBookingResponse createBookingResp = resp.as(CreateBookingResponse.class);
        assertThat(createBookingResp.getBookingid()).isNotNull();
        BookingSteps.bookingsShouldBeEqual(bookingDTO, createBookingResp.getBooking());
    }

    @Test
    @DisplayName("Бронирование: полное обновление данных (PUT)")
    @Tag("API")
    @Tag("REGRESSION")
    @Tag("BOOKING")
    @Severity(SeverityLevel.NORMAL)
    void updateBookingTest() {
        // авторизацию -> создать букинг -> его обновить

        String token = bookingApiClient.auth(CFG.username(), CFG.password())
                .as(AuthResponse.class)
                .getToken();

        Response createResp = bookingApiClient.createBooking(buildBookingRequest());
        assertThat(createResp.getStatusCode()).isEqualTo(200);

        BookingDTO bookingDTO = buildBookingRequest();

        Response updateResponse = bookingApiClient
                .updateBooking(bookingDTO, createResp.as(CreateBookingResponse.class).getBookingid());
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);
        BookingDTO updatedBookingDto = updateResponse.as(BookingDTO.class);

        BookingSteps.bookingsShouldBeEqual(bookingDTO, updatedBookingDto);
    }

    @Test
    @DisplayName("Бронирование: частичное обновление данных (PATCH)")
    @Tag("API")
    @Tag("REGRESSION")
    @Tag("BOOKING")
    @Severity(SeverityLevel.NORMAL)
    void PartialUpdateBookingTest() {

        Response createResp = bookingApiClient.createBooking(buildBookingRequest());
        assertThat(createResp.getStatusCode()).isEqualTo(200);

        BookingDTO bookingDTO = new BookingDTO(
                faker.gravityFalls().character(),
                faker.number().numberBetween(10001, 12000),
                "2026-02-01");

        BookingDTO.BookingDates dates = new BookingDTO.BookingDates();
        dates.setCheckin("2026-02-01");

        bookingDTO.setBookingdates(dates);

        Response updateResponse = bookingApiClient
                .partialUpdateBooking(bookingDTO, createResp.as(CreateBookingResponse.class).getBookingid());
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDTO updatedBookingDto = updateResponse.as(BookingDTO.class);
        assertThat(bookingDTO.getFirstname()).isEqualTo(updatedBookingDto.getFirstname());
        assertThat(bookingDTO.getTotalprice()).isEqualTo(updatedBookingDto.getTotalprice());
        assertThat(bookingDTO.getBookingdates().getCheckin()).isEqualTo(updatedBookingDto.getBookingdates().getCheckin());
    }

    @Test
    @DisplayName("Бронирование: удаление записи и проверка отсутствия")
    @Tag("API")
    @Tag("SMOKE")
    @Tag("BOOKING")
    @Severity(SeverityLevel.CRITICAL)
    void deleteBookingTest() {
        Integer bookingId = bookingApiClient
                .createBooking(buildBookingRequest()).as(CreateBookingResponse.class).getBookingid();

        Response deleteResp = bookingApiClient.deleteBooking(bookingId);
        assertThat(deleteResp.getStatusCode()).isEqualTo(201);

        Response getResp = bookingApiClient.getBooking(bookingId);
        assertThat(getResp.getStatusCode()).isEqualTo(404);
    }

/* один из вариантов, второй вариант через build
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
}