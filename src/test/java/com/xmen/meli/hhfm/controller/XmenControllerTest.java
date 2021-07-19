package com.xmen.meli.hhfm.controller;

import com.xmen.meli.hhfm.model.MutantResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class XmenControllerTest {

    @Autowired
    private XmenController xmenController;

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    private MutantResponse mutantResponse;

    @Test
    public void contextLoads() throws Exception {
        assertThat(xmenController).isNotNull();
    }

    @Test
    void isMutant()  throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mutants",
                String.class)).contains("/mutants");
    }
}