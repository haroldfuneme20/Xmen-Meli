package com.xmen.meli.hhfm.service;

import com.xmen.meli.hhfm.enumeration.KindPerson;
import com.xmen.meli.hhfm.model.StatisticsResponse;
import com.xmen.meli.hhfm.repository.DnaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatsResponseService {

    private DnaRepository dnaRepository;

    public StatsResponseService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public ResponseEntity<StatisticsResponse> statsResponse(){
        double allHumanTest = dnaRepository.count();
        double countHumanDna = dnaRepository.getAllKindPerson(KindPerson.HUMAN);
        double countMutantDna = dnaRepository.getAllKindPerson(KindPerson.MUTANT);
        double average = (countMutantDna/(countHumanDna+countMutantDna));
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setCount_human_dna((int) countHumanDna);
        statisticsResponse.setCount_mutant_dna((int) countMutantDna);
        statisticsResponse.setRatio(average);
        ResponseEntity<StatisticsResponse> response;
        response= new ResponseEntity(statisticsResponse, HttpStatus.BAD_REQUEST);
        return response ;
    }
}
