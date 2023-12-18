package com.example.dushimevalentintermonea.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoMathRequestDTO {
    private double operand1;
    private double operand2;
    private String operation;


    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}