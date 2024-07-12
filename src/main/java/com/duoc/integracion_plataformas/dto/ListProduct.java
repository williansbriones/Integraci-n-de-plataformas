package com.duoc.integracion_plataformas.dto;

import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.List;
@Builder
public record ListProduct(List<LinkedHashMap<String, Object>> Producto) {}
