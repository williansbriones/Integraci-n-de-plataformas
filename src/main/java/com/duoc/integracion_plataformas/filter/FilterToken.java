package com.duoc.integracion_plataformas.filter;

import com.duoc.integracion_plataformas.util.iface.Token;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

import static com.duoc.integracion_plataformas.util.Utils.getCharset;

@RequiredArgsConstructor
@Component
@Order(1)
@Log4j2
public class FilterToken extends OncePerRequestFilter {

  private final Token token;

  @Override
  public boolean shouldNotFilter(HttpServletRequest request) {
    String requestPath = request.getRequestURI();
    return (requestPath.contains("user/create")) || (requestPath.contains("product/get"));
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    log.info("#################### Filter token ####################");
    String tokenUserStr = request.getHeader("token");
    String tokenServerStr = token.getToken();
    //if (!(tokenServerStr.equals(tokenUserStr))) {
    //  ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
    //  //byte[] requestBody = req.getContentAsByteArray();
    //  //log.error("request Body {}", new String(requestBody, getCharset(req.getContentType())));
    //  //log.warn("path not found");
    //  //response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    //  //response.getWriter().write("Not Found ");
    //  throw new ServletException("Token not valid");
    //}
    filterChain.doFilter(request, response);
  }
}
