package com.duoc.integracion_plataformas.dto;

import lombok.Builder;


@Builder
public record TokenLoginDto(String token, Long idClient) {}
