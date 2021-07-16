package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidationMatrixService {



    public Boolean matrixIsCorrect (MutantRequest arrayDNA, String checkCodes) {
        boolean isDNACorrectForm = true;
        int rows = arrayDNA.dna.length;
        Boolean dna_correct = true;
        String arrayUpperCase;

        for (int indexRow = 0; indexRow<rows; indexRow++) {
            // square matrix validation
            char[] column = arrayDNA.dna[indexRow].toCharArray();
            if (rows != column.length) isDNACorrectForm = false;
            // Nitrogen base of DNA validation:  "A", "T", "C", "G"
            arrayUpperCase = arrayDNA.dna[indexRow].toUpperCase();
            if(dna_correct) {
                for (int j = 0; j < arrayUpperCase.toCharArray().length; j++) {
                    if(checkCodes.indexOf(arrayUpperCase.toCharArray()[j])== -1){
                        isDNACorrectForm = false;
                        dna_correct = false;
                    }
                }
            }
        }
        return isDNACorrectForm;
    }
}
