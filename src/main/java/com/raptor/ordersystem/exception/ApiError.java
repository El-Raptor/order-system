package com.raptor.ordersystem.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ApiError {
    private String message;
    private int status;
    private String error;
    private String path;
    private LocalDateTime timestamp;

    public ApiError(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
