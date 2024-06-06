package com.duoc.integracion_plataformas.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import static com.duoc.integracion_plataformas.util.Utils.getCharset;

@RequiredArgsConstructor
@Component
@Order(0)
@Log4j2
public class FilterLog extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws IOException {
    log.info("on first filter");

    ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);


    String requestPath = request.getRequestURI();
    log.info("Path request: {}", requestPath);


    try {

      filterChain.doFilter(req, resp);
    } catch (Exception e) {
      log.error("Error in filter {1},{2}", e);
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("Not Found ");
    } finally {
      byte[] requestBody = req.getContentAsByteArray();
      byte[] responseBody = resp.getContentAsByteArray();
      log.info("request body = {}", new String(requestBody, getCharset(req.getContentType())));
      log.info("response body = {}", new String(responseBody, getCharset(resp.getContentType())));
      resp.copyBodyToResponse();

      log.info("Status of request {}", resp.getStatus());
    }
  }
}
