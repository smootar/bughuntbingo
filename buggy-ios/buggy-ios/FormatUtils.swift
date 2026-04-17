//
//  FormatUtils.swift
//  buggy-ios
//

import Foundation

/// Format a number for the calculator display.
/// Handles special values like infinity, NaN, and limits decimal places.
/// - Parameter value: The display string
/// - Returns: Formatted display string
func formatDisplay(_ value: String) -> String {
    if value.isEmpty { return "0" }
    if value == "Error" { return "Error" }

    // Don't format if user is still typing a decimal
    if value.hasSuffix(".") { return value }

    guard let num = Double(value) else { return "Error" }
    if num.isNaN || num.isInfinite { return "Error" }

    // If it has a decimal part, limit precision
    if value.contains(".") || num.truncatingRemainder(dividingBy: 1) != 0 {
        let formatted = String(format: "%.12g", num)
        return formatted
    }

    return String(format: "%.0f", num)
}

/// Format a history entry string.
/// - Parameters:
///   - a: Left operand
///   - op: Operator string
///   - b: Right operand
///   - result: Calculation result
/// - Returns: Formatted history string like "2 + 3 = 5"
func formatHistoryEntry(_ a: Double, _ op: String, _ b: Double, _ result: Double) -> String {
    let opSymbol: String
    switch op {
    case "+": opSymbol = "+"
    case "-": opSymbol = "\u{2212}"
    case "*": opSymbol = "\u{00d7}"
    case "/": opSymbol = "\u{00f7}"
    default: opSymbol = op
    }

    let aStr = formatDisplay(String(a))
    let bStr = formatDisplay(String(b))
    let rStr = formatDisplay(String(result))
    return "\(aStr) \(opSymbol) \(bStr) = \(rStr)"
}
