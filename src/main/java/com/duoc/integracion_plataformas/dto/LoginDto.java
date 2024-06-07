package com.duoc.integracion_plataformas.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record LoginDto(
    @NonNull
    String email,
    @Nonnull
    String password
    ){
}
