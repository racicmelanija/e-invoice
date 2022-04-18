package com.apigateway;

import com.apigateway.annotation.Authorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    @Authorize(roles = {"ROLE_USER"})
    public JwtAuthenticationToken testIt(JwtAuthenticationToken jwt){
        jwt.getTokenAttributes().get("roles");
        return jwt;
    }
}
