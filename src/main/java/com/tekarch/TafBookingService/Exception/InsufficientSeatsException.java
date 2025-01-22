package com.tekarch.TafBookingService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientSeatsException extends RuntimeException{
    public InsufficientSeatsException(String message) {
        super(message);
    }
}
