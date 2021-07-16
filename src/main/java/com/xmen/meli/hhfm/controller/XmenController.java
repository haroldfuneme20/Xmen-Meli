package com.xmen.meli.hhfm.controller;

import com.xmen.meli.hhfm.model.MutantRequest;
import com.xmen.meli.hhfm.model.MutantResponse;
import com.xmen.meli.hhfm.service.LookingForMutants;
import com.xmen.meli.hhfm.service.ValidationMatrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class XmenController {

    boolean isDNACorrectForm = true;
    String CODES = "ATCG";
    private final ValidationMatrixService validationMatrix;
    private final LookingForMutants lookingForMutants;

    public XmenController(ValidationMatrixService validationMatrix, LookingForMutants lookingForMutants) {
        this.validationMatrix = validationMatrix;
        this.lookingForMutants = lookingForMutants;
    }


    @PostMapping("/mutants")
    public ResponseEntity<MutantResponse> isMutant(@RequestBody MutantRequest arrayDNA) {
        MutantResponse mutantResponse = new MutantResponse();
        ResponseEntity<MutantResponse> response;
//      System.out.println("Request   \n" + dna.dnaToString());

        isDNACorrectForm = validationMatrix.matrixIsCorrect(arrayDNA, CODES);

        if (isDNACorrectForm) {
            if (lookingForMutants.checkDNA(arrayDNA)) {
                mutantResponse.setIs_mutant(true);
                mutantResponse.setMessage("mutant found successfully");
                mutantResponse.setStatusCode("200");
                response = new ResponseEntity(mutantResponse, HttpStatus.OK);
                isDNACorrectForm = true;
            } else {
                mutantResponse.setIs_mutant(false);
                mutantResponse.setMessage("mutant not found");
                mutantResponse.setStatusCode("403");
                response = new ResponseEntity(mutantResponse, HttpStatus.FORBIDDEN);
                isDNACorrectForm = true;
            }

        } else {
            mutantResponse.setIs_mutant(false);
            mutantResponse.setMessage("the matrix is not square or DNA is misspelled ");
            mutantResponse.setStatusCode("400");
            response = new ResponseEntity(mutantResponse, HttpStatus.BAD_REQUEST);
            isDNACorrectForm = true;
        }

        return response;

    }

}
