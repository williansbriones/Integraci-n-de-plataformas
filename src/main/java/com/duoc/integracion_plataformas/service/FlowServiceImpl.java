package com.duoc.integracion_plataformas.service;

import com.duoc.integracion_plataformas.dto.FlowResponseDto;
import com.duoc.integracion_plataformas.exeption.FlowException;
import com.duoc.integracion_plataformas.service.iface.FlowService;
import com.duoc.integracion_plataformas.util.iface.FlowUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.UUID;


@RequiredArgsConstructor
@Service
@Log4j2
public class FlowServiceImpl implements FlowService {

  private final RestClient restClient = RestClient.create();
  private final FlowUtils flowUtils;
  private final String flowEndPoint = "https://sandbox.flow.cl/api";

  @Override
  @SneakyThrows
  public FlowResponseDto CreateOrder(int amount, String email) {
    log.info("generando orden de pago por {}", amount);
    LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("apiKey", flowUtils.getKey());
    params.add("subject", "Pago Online");
    params.add("currency", "CLP");
    params.add("amount", Integer.toString(amount));
    params.add("email", email);
    params.add("commerceOrder", UUID.randomUUID().toString());
    params.add(
        "urlConfirmation", "http://107.23.165.138:8080/invoice/confirm");
    params.add("urlReturn", "https://7915p2bywd.execute-api.us-east-1.amazonaws.com/dev/redirection");
    String sing = flowUtils.createSing(params);
    params.add("s", sing);
    return restClient
        .post()
        .uri(this.flowEndPoint + "/payment/create")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(params)
        .retrieve()
        .onStatus(
            HttpStatusCode::is4xxClientError,
            ((request, response) -> {
              throw new FlowException(
                  Integer.toString(response.getStatusCode().value()),
                  response.getStatusText(),
                  response.getStatusCode());
            }))
        .body(FlowResponseDto.class);
  }

  @SneakyThrows
  public LinkedMultiValueMap<String, String> param() {
    LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("apiKey", flowUtils.getKey());
    params.add("subject", "Pago de prueba");
    params.add("currency", "CLP");
    params.add("amount", "5000");
    params.add("email", "wbriones2013@gmail.com");
    params.add("commerceOrder", "commerceOrder");
    params.add(
        "urlConfirmation", "http://flowosccomerce.tuxpan.com/csepulveda/api2/pay/confirmPay.php");
    params.add("urlReturn", "http://flowosccomerce.tuxpan.com/csepulveda/api2/pay/resultPay.php");
    String sing = flowUtils.createSing(params);
    params.add("s", sing);

    return params;
  }
}
