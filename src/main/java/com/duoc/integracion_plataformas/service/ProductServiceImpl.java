package com.duoc.integracion_plataformas.service;

import com.duoc.integracion_plataformas.dto.ProductExternalDto;
import com.duoc.integracion_plataformas.exeption.UserException;
import com.duoc.integracion_plataformas.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final RestClient restClient = RestClient.create();

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
}
