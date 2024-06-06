package com.duoc.integracion_plataformas.util;

import org.springframework.stereotype.Component;

@Component
public class Letters {
  public static String upperFirstLetter(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
  }

  public static String lowerAllLetter(String str) {
    return str.toLowerCase();
  }
}
