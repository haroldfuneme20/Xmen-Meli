package com.xmen.meli.hhfm.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MutantRequest implements Serializable {
    public String[] dna;

    public String dnaToString() {
        String dnaString = "";
        for (int i = 0; i < dna.length; i++) {
            dnaString += dna[i] + "\n";
        }
        return dnaString;
    }
}
