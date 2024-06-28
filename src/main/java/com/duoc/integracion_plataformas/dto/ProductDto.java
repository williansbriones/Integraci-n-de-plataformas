package com.duoc.integracion_plataformas.dto;


 import lombok.Builder;

@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        String Category,
        Integer price,
        Integer count,
        String image
) {}
