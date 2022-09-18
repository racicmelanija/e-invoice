package com.einvoice.companyservice.service;

import com.einvoice.companyservice.repository.CollaborationRepository;
import com.einvoice.companyservice.service.result.GetCollaborationsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCollaborations {

    private final CollaborationRepository collaborationRepository;

    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public Page<GetCollaborationsResult> execute(UUID companyId, int page, int size) {
        return collaborationRepository.getCollaborations(String.valueOf(companyId), PageRequest.of(page, size));
    }
}
