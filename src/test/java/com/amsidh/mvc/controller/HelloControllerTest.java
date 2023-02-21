package com.amsidh.mvc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    Logger logger = LoggerFactory.getLogger(HelloControllerTest.class);
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @RepeatedTest(10)
    public void test(RepetitionInfo repetitionInfo) {
        logger.info("Current Repetition is " + repetitionInfo.getCurrentRepetition());
        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/hello", String.class);
        logger.info(String.format("Response %s", responseEntity.getBody()));
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals("Hello World", responseEntity.getBody());
    }
}
