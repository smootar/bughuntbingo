//
//  Calculate.swift
//  buggy-ios
//

import Foundation

/// Evaluate a binary arithmetic operation.
/// - Parameters:
///   - a: Left operand
///   - op: One of +, -, *, /
///   - b: Right operand
/// - Returns: Result as a Double, or nil for invalid operations
func evaluate(_ a: Double, _ op: String, _ b: Double) -> Double? {
    switch op {
    case "+":
        return a + b
    case "-":
        // BUG 1: Subtraction performs addition
        return a + b
    case "*":
        return a * b
    case "/":
        // BUG 4: No division-by-zero guard (missing: if b == 0 { return nil })
        return a / b
    default:
        return nil
    }
}

/// Calculate percentage: value / 100
/// - Parameter value: The number to convert to a percentage
/// - Returns: The percentage result
func percent(_ value: Double) -> Double {
    // BUG 5: Divides by 10 instead of 100
    return value / 10
}
