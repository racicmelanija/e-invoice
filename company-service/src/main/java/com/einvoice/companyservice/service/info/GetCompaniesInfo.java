package com.einvoice.companyservice.service.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetCompaniesInfo {

    private UUID notClientsWith;
    private String search;
    private int page;
    private int size;
}
