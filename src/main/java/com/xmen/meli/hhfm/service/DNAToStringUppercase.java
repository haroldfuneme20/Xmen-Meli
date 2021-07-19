package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.model.MutantRequest;
import org.springframework.stereotype.Service;

@Service
public class DNAToStringUppercase {
    public String DnaToString(MutantRequest arrayDNA){
        String msg = "";
        for (int i=0; i<arrayDNA.dna.length; i++){
            msg= msg + arrayDNA.dna[i].toUpperCase() + "-";
        }
        return msg;
    }
}
