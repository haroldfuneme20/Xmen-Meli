package com.xmen.meli.hhfm.controller;

import com.xmen.meli.hhfm.entetity.DNAEntity;
import com.xmen.meli.hhfm.enumeration.KindPerson;
import com.xmen.meli.hhfm.model.MutantRequest;
import com.xmen.meli.hhfm.model.MutantResponse;
import com.xmen.meli.hhfm.model.StatisticsResponse;
import com.xmen.meli.hhfm.repository.DnaRepository;
import com.xmen.meli.hhfm.service.DNAToStringUppercase;
import com.xmen.meli.hhfm.service.LookingForMutants;
import com.xmen.meli.hhfm.service.ValidationMatrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/")
public class XmenController {

    boolean isDNACorrectForm = true;
    String CODES = "ATCG";
    private final ValidationMatrixService validationMatrix;
    private final LookingForMutants lookingForMutants;
    private final DNAToStringUppercase dnaToString;
    DnaRepository dnaRepository;
    KindPerson kindPerson;

    public XmenController(ValidationMatrixService validationMatrix, LookingForMutants lookingForMutants, DNAToStringUppercase dnaToString, DnaRepository dnaRepository) {
        this.validationMatrix = validationMatrix;
        this.lookingForMutants = lookingForMutants;
        this.dnaToString = dnaToString;
        this.dnaRepository = dnaRepository;
    }

    @PostMapping("/mutant")
    public ResponseEntity<MutantResponse> isMutant(@Valid @RequestBody MutantRequest arrayDNA) {
        MutantResponse mutantResponse = new MutantResponse();
        ResponseEntity<MutantResponse> response;
        String msg;
        isDNACorrectForm = validationMatrix.matrixIsCorrect(arrayDNA, CODES);
        msg = dnaToString.DnaToString(arrayDNA);
        DNAEntity dnaEntity = new DNAEntity();
        if (isDNACorrectForm) {
            dnaEntity.setDna(msg);
            if (lookingForMutants.checkDNA(arrayDNA)) {
                try {
                    dnaEntity.setKindPerson(kindPerson.MUTANT);
                    dnaRepository.save(dnaEntity);
                }catch (Exception e){
                    // repetido
                    mutantResponse.setMessage("mutant found successfully, DNA already exist");
                    System.out.println("Error HHFM: " +e);
                }
                mutantResponse.setIs_mutant(true);
                mutantResponse.setMessage("mutant found successfully");
                mutantResponse.setStatusCode("200");
                response = new ResponseEntity(mutantResponse, HttpStatus.OK);
                isDNACorrectForm = true;
            } else {
                try {
                dnaEntity.setKindPerson(kindPerson.HUMAN);
                dnaRepository.save(dnaEntity);
                }catch (Exception e){
                    // repetido
                    mutantResponse.setMessage("mutant not found, DNA already exist");
                    System.out.println("Error HHFM: " +e + "  --- CAOUSE: "+  e.getCause().getMessage());
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

    @GetMapping("/stats")
    public ResponseEntity<StatisticsResponse> statisticsDna(){
        double allHumanTest = dnaRepository.count();
        long countHumanDna = dnaRepository.getAllKindPerson(KindPerson.HUMAN);
        long countMutantDna = dnaRepository.getAllKindPerson(KindPerson.MUTANT);
        double average = countMutantDna/allHumanTest;
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setCount_human_dna((int) countHumanDna);
        statisticsResponse.setCount_mutant_dna((int) countMutantDna);
        statisticsResponse.setRatio(average);
        System.out.println("average  =="+average);
        ResponseEntity<StatisticsResponse> response;
        response= new ResponseEntity(statisticsResponse, HttpStatus.BAD_REQUEST);
        return response ;
    }

}
