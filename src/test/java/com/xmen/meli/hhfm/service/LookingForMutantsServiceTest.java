package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LookingForMutantsServiceTest {

    @Mock
    LookingForMutantsService lookingForMutantsService = new LookingForMutantsService();
    @Mock
    MutantRequest mutantRequest = new MutantRequest();


    @Test
    void checkDNATrue() {
        final String[] dna = {   "AgGtGt", "CAGTGt", "TTcTGc", "AtcAGt", "CCaCTt", "CCaCTa" };
        mutantRequest.setDna(dna);
        boolean response = lookingForMutantsService.checkDNA(mutantRequest);
        System.out.println("*****  "+ response);
        assertEquals(response, false);
    }

    @Test
    void checkDNAFalse() {
        final String[] dna = {   "ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG" };
        mutantRequest.setDna(dna);
        boolean response = lookingForMutantsService.checkDNA(mutantRequest);
        System.out.println("*****  "+ response);
        assertEquals(response, true);
    }
}