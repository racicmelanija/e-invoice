package com.einvoice.companyservice;

import com.einvoice.companyservice.util.KeycloakUtil;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final Keycloak keycloak;
    private final KeycloakUtil keycloakUtil;

    @Value("${keycloak.realm}")
    private String realm;

    @GetMapping(value = "/")
    public JwtAuthenticationToken testIt(JwtAuthenticationToken jwt) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername("tester1");
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("tom+tester1@tdlabs.local");

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        try (Response response = usersResource.create(user)) {
            System.out.println(keycloakUtil.getUserId(response));
        }
        return jwt;
    }
}
