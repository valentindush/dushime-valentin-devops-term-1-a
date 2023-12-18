package com.example.dushimevalentintermonea.serviceImpl;

import com.example.dushimevalentintermonea.exceptions.InvalidOperationException;
import com.example.dushimevalentintermonea.services.IMathOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathOperatorImplTest {

    private IMathOperator mathOperator;

    @BeforeEach
    void setUp() {
        mathOperator = new MathOperatorImpl();
    }

    @Test
    void testDoMathMultiplication() throws InvalidOperationException {
        double result = mathOperator.doMath(2, 3, "*");
        assertEquals(6, result, 0.001);
    }

    @Test
    void testDoMathDivision() throws InvalidOperationException {
        double result = mathOperator.doMath(6, 2, "/");
        assertEquals(3, result, 0.001);
    }

    @Test
    void testDoMathAddition() throws InvalidOperationException {
        double result = mathOperator.doMath(5, 3, "+");
        assertEquals(8, result, 0.001);
    }

    @Test
    void testDoMathSubtraction() throws InvalidOperationException {
        double result = mathOperator.doMath(8, 3, "-");
        assertEquals(5, result, 0.001);
    }

    @Test
    void testDoMathUnknownOperation() {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(2, 3, "unknown"));
    }

    @Test
    void testDoMathDivisionByZero() {
        assertThrows(InvalidOperationException.class, () -> mathOperator.doMath(5, 0, "/"));
    }
}