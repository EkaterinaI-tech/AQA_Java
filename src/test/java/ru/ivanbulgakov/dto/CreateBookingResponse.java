package ru.ivanbulgakov.dto;

import lombok.Data;

@Data
public class CreateBookingResponse {
    private Integer bookingid;
    private BookingDTO booking;
}
