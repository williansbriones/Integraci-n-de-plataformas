package com.duoc.integracion_plataformas.util;


import com.duoc.integracion_plataformas.util.iface.FlowUtils;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.List;
import java.util.Objects;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

@RequiredArgsConstructor
@Getter
@Log4j2
@Component
public class FlowUtilsImpl implements FlowUtils {

  @Value("${API_KEY:#{null}}")
  private String key;

  @Value("${SECRET_KEY:#{null}}")
  private String secret;

  @PostConstruct
  private void init() {
    boolean envEmpty = false;

    log.info("check the KEY and SECRET");
    if (key == null) {
      log.error("null KEY");
      envEmpty = true;
    }
    if (secret == null) {
      log.error("null SECRET");

      envEmpty = true;
    }
    if (envEmpty) {
      log.error("Una de las variables de entorno no fue cargada");
      throw new BeanCreationException("Variable de entorno faltante");
    }
  }

  @SneakyThrows
  @Override
  public String createSing(LinkedMultiValueMap<String, String> map) {
    final String algorithm = "HmacSHA256";
    Mac hmac = Mac.getInstance(algorithm);
    SecretKeySpec secretKey = new SecretKeySpec(this.secret.getBytes(StandardCharsets.UTF_8), algorithm);
    hmac.init(secretKey);
    List<String> keys = createListKeys(map);
    StringBuilder param = new StringBuilder();
    for (String key : keys) {
      param.append(key).append(Objects.requireNonNull(map.get(key)).getFirst());
    }
    String paramStr = param.toString();
    byte[] hashBytes = hmac.doFinal(paramStr.getBytes(StandardCharsets.UTF_8));

    HexFormat hexFormat = HexFormat.of().withLowerCase();
    return hexFormat.formatHex(hashBytes);
  }

  @Override
  public List<String> createListKeys(LinkedMultiValueMap<String, String> map) {
    return map.keySet().stream().sorted().toList();
  }
}
