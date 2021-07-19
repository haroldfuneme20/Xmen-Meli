package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.entetity.DNAEntity;
import com.xmen.meli.hhfm.enumeration.KindPerson;
import com.xmen.meli.hhfm.model.MutantRequest;
import com.xmen.meli.hhfm.model.MutantResponse;
import com.xmen.meli.hhfm.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MutantResponseService {
    private final ValidationMatrixService validationMatrix;
    private final LookingForMutantsService lookingForMutantsService;
    private final DNAToStringUppercaseService dnaToString;

    @Autowired
    DnaRepository dnaRepository;

    boolean isDNACorrectForm = true;
    String CODES = "ATCG";


    public MutantResponseService(ValidationMatrixService validationMatrix, LookingForMutantsService lookingForMutantsService, DNAToStringUppercaseService dnaToString) {
        this.validationMatrix = validationMatrix;
        this.lookingForMutantsService = lookingForMutantsService;
        this.dnaToString = dnaToString;
    }

    public ResponseEntity<MutantResponse> mutantResponse(MutantRequest arrayDNA){
        MutantResponse mutantResponse = new MutantResponse();
        DNAEntity dnaEntity = new DNAEntity();
        ResponseEntity<MutantResponse> response;
        String msg;

        isDNACorrectForm = validationMatrix.matrixIsCorrect(arrayDNA, CODES);
        msg = dnaToString.DnaToString(arrayDNA);
        if (isDNACorrectForm) {
            dnaEntity.setDna(msg);
            if (lookingForMutantsService.checkDNA(arrayDNA)) {
                try {
                    dnaEntity.setKindPerson(KindPerson.MUTANT);
                    dnaRepository.save(dnaEntity);
                }catch (Exception e){
                    mutantResponse.setMessage("mutant found successfully, DNA already exist");
                    System.out.println("Error MUTANT: " +e);
                }
                mutantResponse.setIs_mutant(true);
                mutantResponse.setMessage("mutant found successfully");
                mutantResponse.setStatusCode("200");
                response = new ResponseEntity(mutantResponse, HttpStatus.OK);
                isDNACorrectForm = true;
            } else {
                try {
                    dnaEntity.setKindPerson(KindPerson.HUMAN);
                    dnaRepository.save(dnaEntity);
                }catch (Exception e){
                    mutantResponse.setMessage("mutant not found, DNA already exist");
                    System.out.println("Error HUMAN: " +e);
                }
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
