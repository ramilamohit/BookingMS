package com.tekarch.TafBookingService.DTO;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long userId;
    private Long flightId;
    private String status; // e.g., Booked, Cancelled
}
