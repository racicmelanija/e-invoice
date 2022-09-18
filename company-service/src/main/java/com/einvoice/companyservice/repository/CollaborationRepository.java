package com.einvoice.companyservice.repository;

import com.einvoice.companyservice.model.Collaboration;
import com.einvoice.companyservice.model.CompanyClientId;
import com.einvoice.companyservice.service.result.GetCollaborationsResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CollaborationRepository extends JpaRepository<Collaboration, CompanyClientId> {

    @Query(nativeQuery = true,
            value = "SELECT CAST(c2.id AS VARCHAR) as clientId, c2.company_name as clientName, c2.tax_identification_number as clientTaxIdentificationNumber, " +
                    "c2.company_registration_number as clientRegistrationNumber, c2.phone_number as phoneNumber " +
                    "FROM collaboration c1 LEFT OUTER JOIN company c2 ON c1.client_id = c2.id " +
                    "WHERE CAST(c1.company_id AS VARCHAR) = ?1")
    Page<GetCollaborationsResult> getCollaborations(String companyId, Pageable pageable);
}
