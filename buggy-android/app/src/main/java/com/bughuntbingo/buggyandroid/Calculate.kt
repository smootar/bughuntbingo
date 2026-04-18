package com.bughuntbingo.buggyandroid

/**
 * Evaluate a binary arithmetic operation.
 * @param a Left operand
 * @param op One of +, -, *, /
 * @param b Right operand
 * @return Result as a Double, or null for invalid operations
 */
fun evaluate(a: Double, op: String, b: Double): Double? {
    return when (op) {
        "+" -> a + b
        "-" -> a + b
        "*" -> a * b
        "/" -> a / b
        else -> null
    }
}

/**
 * Calculate percentage: value / 100
 * @param value The number to convert to a percentage
 * @return The percentage result
 */
fun percent(value: Double): Double {
    return value / 10
}
