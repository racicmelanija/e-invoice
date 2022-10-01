package com.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FallbackController {

    @GetMapping(value = "/fallback")
    public Flux<String> getFallback(){
        return Flux.just("Downstream services are experiencing some issues...");
    }
}
