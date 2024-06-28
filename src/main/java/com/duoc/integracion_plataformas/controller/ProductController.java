package com.duoc.integracion_plataformas.controller;


import com.duoc.integracion_plataformas.dto.ProductDto;
import com.duoc.integracion_plataformas.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public List<ProductDto> getProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public ProductDto getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }


}
