package com.einvoice.companyservice.service;

import com.einvoice.companyservice.model.Collaboration;
import com.einvoice.companyservice.repository.CollaborationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCollaboration {

    private final CollaborationRepository collaborationRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void execute(UUID companyId, UUID clientId) {
        collaborationRepository.save(Collaboration.builder()
                .companyId(companyId)
                .clientId(clientId)
                .build());
    }
}
