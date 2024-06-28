package com.duoc.integracion_plataformas.service.iface;

import com.duoc.integracion_plataformas.dto.ProductDto;
import com.duoc.integracion_plataformas.dto.ProductExternalDto;

import java.util.List;

public interface ProductService {

    public ProductExternalDto getProductById(int id);

    public ProductDto getProductById(Long id);

    public List<ProductDto> getAllProducts();

}
