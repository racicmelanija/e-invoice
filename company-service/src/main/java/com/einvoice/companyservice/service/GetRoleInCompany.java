package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Role;
import com.einvoice.companyservice.repository.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetRoleInCompany {

    private final EmploymentRepository employmentRepository;

    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public String execute(UUID companyId, UUID userId) {
        Optional<Role> role = employmentRepository.getRole(companyId, userId);
        if (role.isEmpty()) {
            return "";
        }
        return role.get().toString();
    }
}
