package com.duoc.integracion_plataformas.util;

import com.duoc.integracion_plataformas.util.iface.Token;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Getter
@RequiredArgsConstructor
public class TokenImp implements Token {
  @Value("${TOKEN:#{null}}")
  private String token;

  @PostConstruct
  private void init() {
    boolean state = false;
    log.info("Token evaluate");
    if (token == null) {
        log.error("TOKEN is null");
      state = true;
    }
    if(state) {
        log.error("TOKEN no define");
        throw new BeanCreationException("Variable de entorno faltante");

    }
  }
}
