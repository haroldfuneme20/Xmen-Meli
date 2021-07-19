package com.xmen.meli.hhfm.model;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MutantRequest {
    @NotNull
    public String[] dna;
}
