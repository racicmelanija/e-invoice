package com.einvoice.companyservice.controller.dto;

import com.einvoice.companyservice.service.result.GetCollaborationsResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCollaborationsResponse {

    private List<GetCollaborationsResult> collaborations;
    private long totalElements;
}
