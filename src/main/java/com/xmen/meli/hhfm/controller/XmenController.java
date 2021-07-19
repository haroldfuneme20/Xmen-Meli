package com.xmen.meli.hhfm.controller;

import com.xmen.meli.hhfm.model.MutantRequest;
import com.xmen.meli.hhfm.model.MutantResponse;
import com.xmen.meli.hhfm.model.StatisticsResponse;
import com.xmen.meli.hhfm.service.MutantResponseService;
import com.xmen.meli.hhfm.service.StatsResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/")
public class XmenController {

    private final MutantResponseService mutantResponseService;
    private final StatsResponseService statsResponseService;

    public XmenController(MutantResponseService mutantResponseService, StatsResponseService statsResponseService) {
        this.mutantResponseService = mutantResponseService;
        this.statsResponseService = statsResponseService;
    }

    @PostMapping("/mutant")
    public ResponseEntity<MutantResponse> isMutant(@Valid @RequestBody MutantRequest arrayDNA) {
        return mutantResponseService.mutantResponse(arrayDNA);
    }

    @GetMapping("/stats")
    public ResponseEntity<StatisticsResponse> statisticsDna(){
        return statsResponseService.statsResponse();
    }

}
