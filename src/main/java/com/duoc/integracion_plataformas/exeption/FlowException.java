package com.duoc.integracion_plataformas.exeption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public class FlowException extends RuntimeException {
    private final String code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

}
