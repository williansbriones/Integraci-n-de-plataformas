package com.duoc.integracion_plataformas.dto;

import java.util.List;

public record ProductExternalDto(
    int id,
    String title,
    int price,
    String description,
    List<String> images) {}
