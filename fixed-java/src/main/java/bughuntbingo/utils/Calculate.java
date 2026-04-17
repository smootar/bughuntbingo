package bughuntbingo.utils;

public class Calculate {

    public static Object evaluate(double a, String operator, double b) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) return "Error";
                return a / b;
            default:  return "Error";
        }
    }

    public static double percent(double value) {
        return value / 100;
    }
}
