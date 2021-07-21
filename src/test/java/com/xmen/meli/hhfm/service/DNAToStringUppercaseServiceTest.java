package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DNAToStringUppercaseServiceTest {

    @Mock
    DNAToStringUppercaseService dnaToStringUppercaseService = new DNAToStringUppercaseService();

    @Mock
    MutantRequest mutantRequest = new MutantRequest();


    @Test
    public void DnaToString () {
        final String[] dna = {   "AgGtGt", "CAGTGt", "TTcTGc", "AtcAGt", "CCaCTt", "CCaCTa" };

        mutantRequest.setDna(dna);
        String response = dnaToStringUppercaseService.DnaToString(mutantRequest);
        System.out.println("*****  "+ response);
        assertEquals(response, "AGGTGT-CAGTGT-TTCTGC-ATCAGT-CCACTT-CCACTA-");
    }



}