package com.duoc.integracion_plataformas.dto;

import lombok.Builder;

@Builder
public record ProductRequest(
    Long idProducto,
    String nombre,
    int precio ,
    int stock ,
    String descripcion,
    String imagen
) {
}
