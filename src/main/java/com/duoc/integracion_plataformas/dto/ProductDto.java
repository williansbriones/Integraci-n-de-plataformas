package com.duoc.integracion_plataformas.dto;


import com.duoc.integracion_plataformas.enums.CategoryProduct;

public record ProductDto(
        Long id,
        String name,
        String description,
        CategoryProduct Category,
        Integer price,
        Integer count,
        Integer total
) {}
