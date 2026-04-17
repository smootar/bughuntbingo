package bughuntbingo.utils;

/**
 * Evaluate a binary arithmetic operation.
 * Returns a Number result, or "Error" for invalid operations.
 */
public class Calculate {

    public static Object evaluate(double a, String operator, double b) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a + b;
            case "*": return a * b;
            case "/": return a / b;
            default:  return "Error";
        }
    }

    public static double percent(double value) {
        return value / 10;
    }
}
