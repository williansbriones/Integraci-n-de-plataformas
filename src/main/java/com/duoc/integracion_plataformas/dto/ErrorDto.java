package com.duoc.integracion_plataformas.dto;


import java.time.LocalDate;

public record ErrorDto(String code, String message, LocalDate date) {}
