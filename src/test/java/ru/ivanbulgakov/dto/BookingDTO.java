package ru.ivanbulgakov.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class BookingDTO {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }
}
