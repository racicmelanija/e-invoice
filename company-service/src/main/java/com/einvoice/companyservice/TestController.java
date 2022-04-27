package com.einvoice.companyservice;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/")
    public JwtAuthenticationToken testIt(JwtAuthenticationToken jwt){
        return jwt;
    }
}
