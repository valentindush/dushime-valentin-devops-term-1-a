package com.example.dushimevalentintermonea.controller;

import static org.junit.Assert.assertEquals;
import com.example.dushimevalentintermonea.DTO.DoMathRequestDTO;
import com.example.dushimevalentintermonea.payload.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doMathOperation_Success(){
        DoMathRequestDTO dto = new DoMathRequestDTO(2, 5, "+");

        ResponseEntity<ApiResponse> response = this.restTemplate.postForEntity("/api/v1/do_math",dto,ApiResponse.class);

        assertEquals(200, response.getStatusCode().value());
    }
}