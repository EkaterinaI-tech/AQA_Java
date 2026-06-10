package ru.ivanbulgakov.dto;

import lombok.Data;

@Data
public class CreateBookingResponse {
    private Long bookingid;
    private CreateBookingDTO booking;
}
