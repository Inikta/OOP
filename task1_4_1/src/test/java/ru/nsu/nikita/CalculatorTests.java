package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    void emptyTest() {
        String expression = "";
        Calculator calculator =  new Calculator(expression);
        calculator.compute();
        Assertions.assertEquals(0, calculator.getResult());
    }

    @Test
    void illegalArgument() {
        String expression = "+ a i";
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Calculator(expression).compute());
    }

    /*
    * Default expression from task description
    * expression = "sin + - 1 2 1" <=> sin(1 + (1 - 2))
     */
    @Test
    void taskExpressionTest() {
        String expression = "sin + - 1 2 1";
        Calculator calculator =  new Calculator(expression);
        calculator.compute();
        Assertions.assertEquals(0, calculator.getResult());
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
        Calculator calculator =  new Calculator(expression);
        calculator.compute();
        double result = calculator.getResult();
        Assertions.assertEquals(ref, result, 4);
    }

    @Test
    void longExpressionSpacesTest() {
        String expression = "*     -     + 10.5 *  log 5    pow 2 3 * 5 /    cos deg       180 sin   deg90 sqrt    4";
        double ref = 56.751006598945605993612149331619;
        Calculator calculator =  new Calculator(expression);
        calculator.compute();
        double result = calculator.getResult();
        Assertions.assertEquals(ref, result, 4);
    }
}
