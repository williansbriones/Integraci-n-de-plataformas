package com.duoc.integracion_plataformas.util.iface;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.util.LinkedMultiValueMap;

public interface FlowUtils {

  String createSing(LinkedMultiValueMap<String, String> map) throws NoSuchAlgorithmException;

  List<String> createListKeys(LinkedMultiValueMap<String, String> map);

  String getKey();
}
