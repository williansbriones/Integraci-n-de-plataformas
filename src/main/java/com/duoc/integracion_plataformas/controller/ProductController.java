package com.duoc.integracion_plataformas.controller;


import com.duoc.integracion_plataformas.dto.ProductExternalDto;
import com.duoc.integracion_plataformas.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ProductExternalDto getProduct(@RequestParam int id) {
        return productService.getProductById(id);
    }




}
