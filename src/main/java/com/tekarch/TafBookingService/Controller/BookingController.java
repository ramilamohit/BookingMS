package com.tekarch.TafBookingService.Controller;

import com.tekarch.TafBookingService.DTO.BookingResponseDTO;
import com.tekarch.TafBookingService.DTO.BookingRequestDTO;
import com.tekarch.TafBookingService.Service.BookingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;
    private static final Logger logger = LogManager.getLogger(BookingController.class);

    // Book a flight
    @PostMapping
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequestDTO bookingRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingServiceImpl.bookFlight(bookingRequest));
        } catch (Exception e) {
            logger.error("Booking failed");
            // Log the exception for debugging
            e.printStackTrace();
            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Booking failed: " + e.getMessage());
        }
    }

    // Get booking details by booking ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingServiceImpl.getBookingById(bookingId));
    }

    // Get all bookings for a user by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingServiceImpl.getBookingsByUserId(userId));
    }

    // Delete a booking by booking ID
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId) {
        bookingServiceImpl.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}
