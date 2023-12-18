package com.example.dushimevalentintermonea.serviceImpl;

import com.example.dushimevalentintermonea.exceptions.InvalidOperationException;
import com.example.dushimevalentintermonea.services.IMathOperator;
import org.springframework.stereotype.Service;

@Service
public class MathOperatorImpl implements IMathOperator {
    @Override
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException {
        if ("/".equals(operation) && operand2 == (double) 0) {
            throw new InvalidOperationException("Cannot divide by 0");
        }

        switch (operation) {
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            default:
                throw new RuntimeException("Unknown operation");
        }
    }
}
