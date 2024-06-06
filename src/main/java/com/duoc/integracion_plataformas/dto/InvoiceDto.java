package com.duoc.integracion_plataformas.dto;


import com.duoc.integracion_plataformas.enums.StateInvoice;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;
@Builder
public record InvoiceDto(
    Long id,
    List<ProductDto> productos,
    Timestamp fecha,
    StateInvoice estado,
    Integer total,
    Long idCaja,
    UserDto user,
    String token) {}
