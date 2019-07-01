package com.twitter.clone.twitterclone.security;

import com.google.gson.Gson;
import com.twitter.clone.twitterclone.exception.InvalidLoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Entry Point untuk mengecek Token
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException{
        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);


        Map<String, Object> response = new HashMap<>();
        response.put("data", null);
        response.put("message", jsonLoginResponse);
        response.put("error", true);
        response.put("errorCode", 401);

        httpServletResponse.getWriter().print(
                response
        );


    }
}
