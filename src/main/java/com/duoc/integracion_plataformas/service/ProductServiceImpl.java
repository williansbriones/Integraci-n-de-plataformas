package com.duoc.integracion_plataformas.service;

import com.duoc.integracion_plataformas.dto.ProductDto;
import com.duoc.integracion_plataformas.dto.ProductExternalDto;
import com.duoc.integracion_plataformas.entity.ProductEntity;
import com.duoc.integracion_plataformas.exeption.UserException;
import com.duoc.integracion_plataformas.repository.ProductRepository;
import com.duoc.integracion_plataformas.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final RestClient restClient = RestClient.create();
  private final ProductRepository productRepository;

  @Override
  public ProductExternalDto getProductById(int id) {
    return restClient
        .get()
        .uri("https://api.escuelajs.co/api/v1/products/{id}", id)
        .accept(APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new UserException(
                    "error automatico si encuentra un error 400",
                    HttpStatus.BAD_REQUEST,
                    "Error en el servidor");

            }).body(ProductExternalDto.class);
        //.exchange(
        //    (request, response) -> {
        //      if (response.getStatusCode().is4xxClientError()) {
        //        throw new UserException(
        //            "error automatico si encuentra un error 400",
        //            HttpStatus.BAD_REQUEST,
        //            "Error en el servidor");
        //      }
        //      return convertResponse(response);
        //    });
  }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = productRepository.findAll().stream().map(product -> ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .Category(product.getCategory())
                .price(product.getPrice())
                .count(product.getStock())
                .total(product.getTotal())
                .image(product.getImage())
                .build()).toList();

        return products;
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new UserException("Producto no encontrado", HttpStatus.NOT_FOUND, "Producto no encontrado"));
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .Category(product.getCategory())
                .price(product.getPrice())
                .count(product.getStock())
                .total(product.getTotal())
                .image(product.getImage())
                .build();
    }

}
