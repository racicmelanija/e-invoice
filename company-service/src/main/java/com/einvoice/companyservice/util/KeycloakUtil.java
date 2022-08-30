package com.einvoice.companyservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.UUID;

@Component
public class KeycloakUtil {

    @Value("${keycloak.location_path}")
    private String locationPath;

    public UUID getUserId(Response response) {
        if(response.getLocation() == null) {
            throw new RuntimeException();
        }
        return UUID.fromString(response.getLocation().getPath().replace(locationPath, ""));
    }

}
