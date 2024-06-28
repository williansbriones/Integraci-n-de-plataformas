package com.duoc.integracion_plataformas.dto;


import com.duoc.integracion_plataformas.enums.CategoryProduct;
import lombok.Builder;

@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        CategoryProduct Category,
        Integer price,
        Integer count,
        Integer total,
        String image
) {}
