package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ValidationMatrixServiceTest {

    @Mock
    ValidationMatrixService validationMatrixService = new ValidationMatrixService();
    @Mock
    MutantRequest mutantRequest = new MutantRequest();



    @Test
    void matrixIsCorrectTrue() {
        String CODES = "ATCG";
        final String[] dna = {   "AgGtGt", "CAGTGt", "TTcTGc", "AtcAGt", "CCaCTt", "CCaCTa" };
        mutantRequest.setDna(dna);
        boolean response = validationMatrixService.matrixIsCorrect(mutantRequest,CODES );
        assertEquals(response, true);
    }

    @Test
    void matrixIsCorrectFalse() {
        String CODES = "ATCG";
        final String[] dna = {   "AgGtGt", "xXxXxX", "TTcTGc", "AtcAGt", "CCaCTt", "CCaCTa" };
        mutantRequest.setDna(dna);
        boolean response = validationMatrixService.matrixIsCorrect(mutantRequest,CODES );
        assertEquals(response, false);
    }

    @Test
    void matrixIsCorrectEmpty() {
        String CODES = "ATCG";
        final String[] dna = { };
        mutantRequest.setDna(dna);
        boolean response = validationMatrixService.matrixIsCorrect(mutantRequest,CODES );
        assertEquals(response, false);
    }

    @Test
    void matrixIsCorrectIncorrect() {
        String CODES = "ATCG";
        final String[] dna = { "AgGtGt", "TTcTGca", "TTcTGcaa", "AtcAGt", "CCaCTt", "CCaCTa"};
        mutantRequest.setDna(dna);
        boolean response = validationMatrixService.matrixIsCorrect(mutantRequest,CODES );
        assertEquals(response, false);
    }
}