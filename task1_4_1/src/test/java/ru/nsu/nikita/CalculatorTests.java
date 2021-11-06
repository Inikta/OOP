package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    void emptyTest() {
        String expression = "";
        double result =  new Calculator(expression).getResult();
        Assertions.assertEquals(0, result);
    }

    @Test
    void illegalArgument() {
        String expression = "+ a i";
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Calculator(expression).getResult());
    }

    /*
    * Default expression from task description
    * expression = "sin + - 1 2 1" <=> sin(1 + (1 - 2))
     */
    @Test
    void taskExpressionTest() {
        String expression = "sin + - 1 2 1";
        Assertions.assertEquals(0, new Calculator(expression).getResult());
    }

    /*
    * Complicate expression for testing all operations
    * expression = "* - + 10.5 * log 5 pow 2 3 * 5 / cos deg180 sin deg90 sqrt4" <=>
    * <=> ((10.5 + logn(5) * 2^3) - (5 * cos(deg180) / sin(deg90)) * sqrt 4
    * Reference value is computed by Windows default calculator
     */

    @Test
    void longExpressionTest() {
        String expression = "* - + 10.5 * log 5 pow 2 3 * 5 / cos deg180 sin deg90 sqrt4";
        double ref = 56.751006598945605993612149331619;
        Calculator calc = new Calculator(expression);
        double result = calc.getResult();
        Assertions.assertEquals(ref, result, 4);
    }
}
