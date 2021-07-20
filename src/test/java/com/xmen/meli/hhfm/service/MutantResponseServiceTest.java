package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import com.xmen.meli.hhfm.model.MutantResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class MutantResponseServiceTest {

    @Mock
    MutantRequest mutantRequest = new MutantRequest();

    @Mock
    LookingForMutantsService lookingForMutantsService = new LookingForMutantsService();
    @Mock
    ValidationMatrixService validationMatrixService = new ValidationMatrixService();
    @Mock
    DNAToStringUppercaseService dnaToStringUppercaseService = new DNAToStringUppercaseService();

    @InjectMocks
    MutantResponseService mutantResponseService = new MutantResponseService(validationMatrixService, lookingForMutantsService, dnaToStringUppercaseService);

    @Test
    void mutantResponseOk() {
        final String[] dna = {   "AgGtGt", "AAGTGt", "ATaTGc", "AtcAGt", "CCaCTt", "CCaCTa" };
        mutantRequest.setDna(dna);
        ResponseEntity<MutantResponse> response = mutantResponseService.mutantResponse(mutantRequest);
        assertEquals(response.getStatusCodeValue(), 200);
    }

    @Test
    void mutantResponseForbidden() {
        final String[] dna = {   "AgGtGt", "CAGTGt", "TTcTGc", "AtcAGt", "CCaCTt", "CCaCTa" };
        mutantRequest.setDna(dna);
        ResponseEntity<MutantResponse> response = mutantResponseService.mutantResponse(mutantRequest);
        assertEquals(response.getStatusCodeValue(), 403);
    }

    @Test
    void mutantResponseMatrixIncorrect() {
        final String[] dna = {   "AgGtGtaa", "CAGTGt", "TTcTGc", "AtcAGt", "CCaCTt", "CCaCTa" };
        mutantRequest.setDna(dna);
        ResponseEntity<MutantResponse> response = mutantResponseService.mutantResponse(mutantRequest);
        assertEquals(response.getStatusCodeValue(), 400);
    }
}