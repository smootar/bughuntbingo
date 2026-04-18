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
        return a + b
    case "*":
        return a * b
    case "/":
        return a / b
    default:
        return nil
    }
}

/// Calculate percentage: value / 100
/// - Parameter value: The number to convert to a percentage
/// - Returns: The percentage result
func percent(_ value: Double) -> Double {
    return value / 10
}
