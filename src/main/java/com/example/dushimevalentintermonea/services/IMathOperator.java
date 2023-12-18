package com.example.dushimevalentintermonea.services;

import com.example.dushimevalentintermonea.exceptions.InvalidOperationException;

public interface IMathOperator {
    double doMath(double operand1, double operand2, String operation) throws InvalidOperationException;
}
