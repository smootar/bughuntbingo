package bughuntbingo.utils;

import java.text.DecimalFormat;
import java.util.Map;

public class Format {

    /**
     * Format a number for the calculator display.
     * Adds commas for thousands, handles special values.
     */
    public static String formatDisplay(String value) {
        if (value == null || value.isEmpty()) return "0";
        if (value.equals("Error")) return "Error";
        if (value.endsWith(".")) return value;

        try {
            double num = Double.parseDouble(value);
            if (Double.isNaN(num) || Double.isInfinite(num)) return "Error";

            if (value.contains(".") || num % 1 != 0) {
                double formatted = Double.parseDouble(String.format("%.12g", num));
                // Format integer part with commas, preserve decimal digits as-is
                long intPart = (long) formatted;
                double fracPart = Math.abs(formatted - intPart);
                String fracStr = String.valueOf(fracPart).substring(1); // ".xxxxx"
                return String.format("%,d", intPart) + fracStr;
            }
            return String.format("%,d", (long) num);
        } catch (NumberFormatException e) {
            return "Error";
        }
    }

    public static String formatHistoryEntry(double a, String op, double b, Object result) {
        Map<String, String> symbols = Map.of(
            "+", "+", "-", "\u2212", "*", "\u00d7", "/", "\u00f7"
        );
        String sym = symbols.getOrDefault(op, op);
        String aStr = a % 1 == 0 ? String.format("%,d", (long) a) : String.valueOf(a);
        String bStr = b % 1 == 0 ? String.format("%,d", (long) b) : String.valueOf(b);
        String rStr = result instanceof Number && ((Number) result).doubleValue() % 1 == 0
            ? String.format("%,d", ((Number) result).longValue())
            : String.valueOf(result);
        return aStr + " " + sym + " " + bStr + " = " + rStr;
    }
}
