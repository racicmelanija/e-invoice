package com.einvoice.companyservice.controller;

import com.einvoice.companyservice.controller.dto.CreateCollaborationRequest;
import com.einvoice.companyservice.controller.dto.GetCollaborationsResponse;
import com.einvoice.companyservice.service.CreateCollaboration;
import com.einvoice.companyservice.service.GetCollaborations;
import com.einvoice.companyservice.service.result.GetCollaborationsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.einvoice.companyservice.converter.CollaborationConverter.toGetCollaborationsResponse;

@RestController
@RequiredArgsConstructor
public class CollaborationController {

    private final GetCollaborations getCollaborations;
    private final CreateCollaboration createCollaboration;

    @GetMapping("/collaborations")
    public ResponseEntity<GetCollaborationsResponse> getCollaborations(@RequestParam UUID companyId, @RequestParam int page, @RequestParam int size) {
        Page<GetCollaborationsResult> collaborations = getCollaborations.execute(companyId, page, size);
        return new ResponseEntity<>(toGetCollaborationsResponse(collaborations), HttpStatus.OK);
    }

    @PostMapping("/collaborations")
    public ResponseEntity<Void> createCollaboration(@Valid @RequestBody CreateCollaborationRequest request) {
        createCollaboration.execute(request.getCompanyId(), request.getClientId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
