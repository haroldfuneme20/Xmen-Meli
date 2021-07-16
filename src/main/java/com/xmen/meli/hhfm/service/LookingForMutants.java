package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.springframework.stereotype.Service;

@Service
public class LookingForMutants {
    String[] CODES_IS_MUTANT = {"AAAA", "TTTT", "CCCC", "GGGG"};
    boolean mutantDetected= false;

    public boolean checkDNA(MutantRequest arrayDNA) {
        for (String row : arrayDNA.dna) {
            mutantDetected = checkRowsDNA(row.toUpperCase());
            if (mutantDetected) break;
        }
        System.out.println("Response Mutant Test : " + mutantDetected);
        return mutantDetected;
    }

    public boolean checkRowsDNA(String rowUpperCase) {
        boolean checkRowsDNA = false;
        System.out.println("ROW TO TEST " + rowUpperCase);
        for (String dnaMutant: CODES_IS_MUTANT) {
            System.out.println("CHECK ROW DNA MUTANT:  " + rowUpperCase.indexOf(dnaMutant));
            if(rowUpperCase.indexOf(dnaMutant) != -1){
                checkRowsDNA = true;
                break;
            }
        }
        System.out.println("\n");
        return checkRowsDNA;
    }

    public boolean checkColumnsDNA(){
        return true;
    }

}
