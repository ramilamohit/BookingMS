package com.tekarch.TafBookingService.Service;

import com.tekarch.TafBookingService.DTO.BookingResponseDTO;
import com.tekarch.TafBookingService.DTO.BookingRequestDTO;
import com.tekarch.TafBookingService.DTO.FlightDTO;
import com.tekarch.TafBookingService.Exception.InsufficientSeatsException;
import com.tekarch.TafBookingService.Exception.ResourceNotFoundException;
import com.tekarch.TafBookingService.Service.Interface.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Value("${datastore.service.url}")
    private String datastoreServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    // Book a flight
    public BookingResponseDTO bookFlight(BookingRequestDTO bookingRequest) {

        // Create booking
        String bookingUrl = datastoreServiceUrl + "/bookings";
        BookingResponseDTO newBooking = restTemplate.postForObject(bookingUrl, bookingRequest, BookingResponseDTO.class);

        return newBooking;
    }
    // Get booking by ID
    public BookingResponseDTO getBookingById(Long bookingId) {
        String url = datastoreServiceUrl + "/bookings/" + bookingId;
        return restTemplate.getForObject(url, BookingResponseDTO.class);
    }

    // Get bookings by user ID
    public List<BookingResponseDTO> getBookingsByUserId(Long userId) {
        String url = datastoreServiceUrl + "/bookings/user/" + userId;
        ResponseEntity<List<BookingResponseDTO>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    // Delete a booking
    public void deleteBooking(Long bookingId) {
        String url = datastoreServiceUrl + "/bookings/" + bookingId;
        restTemplate.delete(url);
    }

}
