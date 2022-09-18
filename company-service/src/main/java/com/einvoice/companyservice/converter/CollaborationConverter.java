package com.einvoice.companyservice.converter;

import com.einvoice.companyservice.controller.dto.GetCollaborationsResponse;
import com.einvoice.companyservice.service.result.GetCollaborationsResult;
import org.springframework.data.domain.Page;

import java.util.List;

public class CollaborationConverter {

    public static GetCollaborationsResponse toGetCollaborationsResponse(Page<GetCollaborationsResult> result) {
        return GetCollaborationsResponse.builder()
                .collaborations(result.getContent())
                .totalElements(result.getTotalElements())
                .build();
    }
}
