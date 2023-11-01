package com.example.personal_productserviceproxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    @DisplayName("Testing 1+2 = 3")
    public void TEST_WHENADDTWOINT_THENRETURNINT(){
        Calculator calculator = new Calculator();
        int result= calculator.add(1, 2);
        assertEquals(3, result);
    }

    @Test
    public void TEST_WHENDIVIDEZEROTHENEXCEPTION() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.div(1, 0));
    }

}
