package com.apigateway;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
    private final WebClient.Builder webClientBuilder;

    public TestController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping(value = "/test")
    public Mono<JwtAuthenticationToken> testIt(JwtAuthenticationToken jwt){
        return webClientBuilder.build()
                .get()
                .uri("http://company-service/")
                .headers(h -> h.setBearerAuth(String.valueOf(jwt.getToken().getTokenValue())))
                .retrieve()
                .bodyToMono(JwtAuthenticationToken.class);
    }
}
