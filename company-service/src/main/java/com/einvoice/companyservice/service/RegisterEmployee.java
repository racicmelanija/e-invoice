package com.einvoice.companyservice.service;

import com.einvoice.companyservice.exception.UnauthorizedException;
import com.einvoice.companyservice.model.Employment;
import com.einvoice.companyservice.model.User;
import com.einvoice.companyservice.repository.EmploymentRepository;
import com.einvoice.companyservice.repository.UserRepository;
import com.einvoice.companyservice.service.info.RegisterEmployeeInfo;
import com.einvoice.companyservice.util.KeycloakUtil;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterEmployee {

    @Value("${keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;
    private final KeycloakUtil keycloakUtil;
    private final UserRepository userRepository;
    private final EmploymentRepository employmentRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(RegisterEmployeeInfo info) {
        if(!employmentRepository.isAdminOrOwnerOfCompany(info.getCompanyId(), info.getAdminId())) {
            throw new UnauthorizedException();
        }

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();
        UserRepresentation user = getUserRepresentation(info);

        try (Response response = usersResource.create(user)) {
            final UUID userId = keycloakUtil.getUserId(response);

            userRepository.save(new User(userId));
            sendEmailForPasswordUpdate(usersResource, userId);
            registerEmployment(info, userId);
        }
    }

    private UserRepresentation getUserRepresentation(RegisterEmployeeInfo info) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
        user.setEmail(info.getEmail());
        user.setRealmRoles(new ArrayList<>(){{
            add(info.getRole().toString());
        }});
        return user;
    }

    private void sendEmailForPasswordUpdate(UsersResource usersResource, UUID userId) {
        UserResource registeredUser = usersResource.get(String.valueOf(userId));
        registeredUser.executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    private void registerEmployment(RegisterEmployeeInfo info, UUID userId) {
        employmentRepository.save(Employment.builder()
                .companyId(info.getCompanyId())
                .employeeId(userId)
                .role(info.getRole())
                .build());
    }
}
