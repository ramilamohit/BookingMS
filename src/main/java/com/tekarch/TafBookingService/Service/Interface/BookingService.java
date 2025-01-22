package com.tekarch.TafBookingService.Service.Interface;

import com.tekarch.TafBookingService.DTO.BookingResponseDTO;
import com.tekarch.TafBookingService.DTO.BookingRequestDTO;

import java.util.List;

public interface BookingService {
    BookingResponseDTO bookFlight(BookingRequestDTO bookingRequest);
    BookingResponseDTO getBookingById(Long bookingId);
    List<BookingResponseDTO> getBookingsByUserId(Long userId);
    void deleteBooking(Long bookingId);
}
