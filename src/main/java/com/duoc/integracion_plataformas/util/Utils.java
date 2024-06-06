package com.duoc.integracion_plataformas.util;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static Charset getCharset(String contentType) {
        try {
            return Optional.ofNullable(contentType)
                    .map(MediaType::parseMediaType)
                    .map(MediaType::getCharset)
                    .orElse(StandardCharsets.UTF_8);
        } catch (Exception e) {
            return StandardCharsets.UTF_8;
        }
    }
}
