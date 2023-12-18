package com.example.dushimevalentintermonea.controllers;

import com.example.dushimevalentintermonea.DTO.DoMathRequestDTO;
import com.example.dushimevalentintermonea.exceptions.InvalidOperationException;
import com.example.dushimevalentintermonea.payload.ApiResponse;
import com.example.dushimevalentintermonea.serviceImpl.MathOperatorImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/do_math")
public class MathController {
    private final MathOperatorImpl mathOperatorImpl;
    public MathController(MathOperatorImpl mathOperatorImpl) {
        this.mathOperatorImpl = mathOperatorImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody DoMathRequestDTO dto) throws InvalidOperationException {
        return ResponseEntity.ok(ApiResponse.success(mathOperatorImpl.doMath(dto.getOperand1(), dto.getOperand2(), dto.getOperation())));
    }
}

