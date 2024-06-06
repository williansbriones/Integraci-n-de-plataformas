package com.duoc.integracion_plataformas.exeption;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserException extends RuntimeException {
    private final String code;
    private final HttpStatus request;
    private LocalDate date = LocalDate.now();

    public UserException(String code, HttpStatus request, String message, LocalDate date) {
        super(message);
        this.code = code;
        this.request = request;

    }

    public UserException(String code, HttpStatus request, String message) {
        super(message);
        this.code = code;
        this.request = request;

    }
}
