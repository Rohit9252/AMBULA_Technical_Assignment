package com.userLocation.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class EntryPoint implements AuthenticationEntryPoint {


    /**
     * Handles the authentication entry point for unauthorized requests.
     *
     * @param request       the HTTP servlet request
     * @param response      the HTTP servlet response
     * @param authException the authentication exception
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet-related exception occurs
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final Map<String, Object> map = new HashMap<>();
        map.put("error", authException.getMessage());
        map.put("message", "You are not authorized to access this resource");
        map.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("path", request.getServletPath());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), map);
    }


}
