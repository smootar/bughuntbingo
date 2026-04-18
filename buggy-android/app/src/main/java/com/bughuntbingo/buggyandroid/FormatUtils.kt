package com.bughuntbingo.buggyandroid

import java.text.DecimalFormat

/**
 * Format a number for the calculator display.
 * Handles special values like infinity, NaN, and limits decimal places.
 * @param value The display string
 * @return Formatted display string
 */
fun formatDisplay(value: String): String {
    if (value.isEmpty()) return "0"
    if (value == "Error") return "Error"

    // Don't format if user is still typing a decimal
    if (value.endsWith(".")) return value

    val num = value.toDoubleOrNull() ?: return "Error"
    if (num.isNaN() || num.isInfinite()) return "Error"

    // If it has a decimal part, limit precision
    if (value.contains(".") || num % 1 != 0.0) {
        val format = DecimalFormat("#.############")
        return format.format(num)
    }

    return num.toLong().toString()
}

/**
 * Format a history entry string.
 * @param a Left operand
 * @param op Operator string
 * @param b Right operand
 * @param result Calculation result
 * @return Formatted history string like "2 + 3 = 5"
 */
fun formatHistoryEntry(a: Double, op: String, b: Double, result: Double): String {
    val opSymbol = when (op) {
        "+" -> "+"
        "-" -> "\u2212"
        "*" -> "\u00d7"
        "/" -> "\u00f7"
        else -> op
    }

    val aStr = formatDisplay(a.toString())
    val bStr = formatDisplay(b.toString())
    val rStr = formatDisplay(result.toString())
    return "$aStr $opSymbol $bStr = $rStr"
}
