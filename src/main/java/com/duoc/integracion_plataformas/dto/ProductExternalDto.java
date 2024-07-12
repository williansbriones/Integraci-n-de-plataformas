package com.duoc.integracion_plataformas.dto;

import java.util.List;

public record ProductExternalDto(
    int idProducto,
    String nombre,
    int precio,
    String description,
    List<String> images) {}
