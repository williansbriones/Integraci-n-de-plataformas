package com.duoc.integracion_plataformas.service;

import com.duoc.integracion_plataformas.dto.ListProduct;
import com.duoc.integracion_plataformas.dto.ProductDto;
import com.duoc.integracion_plataformas.dto.ProductRequest;
import com.duoc.integracion_plataformas.entity.ProductEntity;
import com.duoc.integracion_plataformas.exeption.UserException;
import com.duoc.integracion_plataformas.repository.ProductRepository;
import com.duoc.integracion_plataformas.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final RestClient restClient = RestClient.create();
  private final ProductRepository productRepository;

  //@Override
  public ListProduct getAllProductsExternal() {
      var requestProduct = restClient
        .get()
        .uri("https://aplicaciontestferreteria.azurewebsites.net/api/producto")
        .accept(APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new UserException(
                    "error automatico si encuentra un error 400",
                    HttpStatus.BAD_REQUEST,
                    "Error en el servidor");

            }).body(List.class);

    return ListProduct.builder().Producto(requestProduct).build();

  }

    public ProductRequest getProductsExternal(Long id) {
        var requestProduct = restClient
                .get()
                .uri("https://aplicaciontestferreteria.azurewebsites.net/api/producto/{id}", id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new UserException(
                            "error automatico si encuentra un error 400",
                            HttpStatus.BAD_REQUEST,
                            "Error en el servidor");

                }).body(ProductRequest.class);

        return requestProduct;

    }

    @Override
    public List<ProductDto> getAllProducts() {

        ListProduct products = getAllProductsExternal();
        System.out.println(products.Producto().getFirst().get("idProducto"));
        List<ProductDto> productos = products.Producto().stream().map(product ->
                ProductDto.builder()
                .id(Long.parseLong(product.get("idProducto").toString()))
                .name(product.get("nombre").toString())
                .description(product.get("descripcion").toString())
                .price(Integer.parseInt(product.get("precio").toString()))
                .count(Integer.parseInt(product.get("stock").toString()))
                .image(product.get("imagen").toString())
                .build()).toList();
        log.info("Productos consultados: {}", productos);
        return productos;
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new UserException("Producto no encontrado", HttpStatus.NOT_FOUND, "Producto no encontrado"));

        var requestProduct = getProductsExternal(id);

        return ProductDto.builder()
                .id(requestProduct.idProducto())
                .name(requestProduct.nombre())
                .description(requestProduct.descripcion())
                .price(requestProduct.precio())
                .count(requestProduct.stock())
                .image(requestProduct.imagen())
                .build();
    }

}

//ProductDto.builder()
// .id(product.getId())
// .name(product.getName())
// .description(product.getDescription())
// .Category(product.getCategory())
// .price(product.getPrice())
// .count(product.getStock())
// .image(product.getImage())
// .build();


//List<ProductDto> products = productRepository.findAll().stream().map(product -> ProductDto.builder()
//        .id(product.getId())
//        .name(product.getName())
//        .description(product.getDescription())
//        .Category(product.getCategory())
//        .price(product.getPrice())
//        .count(product.getStock())
//        .image(product.getImage())
//        .build()).toList();