package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    void emptyTest() {
        String expression = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Calculator(expression).getResult());
    }

    @Test
    void taskExpressionTest() {
        String expression = "sin + - 1 2 1";
        Assertions.assertEquals(0, new Calculator(expression).getResult());
    }

   @Test
    void longExpressionTest() {
       String expression = "- + 10.5 * log 5 pow 2 3 * 5 / cos deg180 sin deg90";
       double ref = 28.37550329947280299680607466581;
       Calculator calc = new Calculator(expression);
       double result = calc.getResult();
       Assertions.assertEquals(ref, result);
   }
}
