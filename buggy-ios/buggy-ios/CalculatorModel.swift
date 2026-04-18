//
//  CalculatorModel.swift
//  buggy-ios
//

import Foundation
import Observation

@Observable
class CalculatorModel {
    var display: String = "0"
    var previousOperand: Double? = nil
    var currentOperator: String? = nil
    var waitingForOperand: Bool = false
    var memory: Double = 0
    var history: [String] = []
    var expression: String = ""

    func handleDigit(_ digit: String) {
        if waitingForOperand {
            display = digit
            waitingForOperand = false
        } else {
            display = display == "0" ? digit : display + digit
        }
    }

    func handleDecimal() {
        if waitingForOperand {
            display = "0."
            waitingForOperand = false
            return
        }
        display = display + "."
    }

    func handleOperator(_ nextOperator: String) {
        let current = Double(display) ?? 0

        previousOperand = current

        currentOperator = nextOperator
        waitingForOperand = true

        if let prev = previousOperand, let op = currentOperator, !waitingForOperand {
            if let result = evaluate(prev, op, current) {
                expression = "\(result) \(nextOperator)"
            }
        } else {
            expression = "\(current) \(nextOperator)"
        }
    }

    func handleEquals() {
        guard let prev = previousOperand, let op = currentOperator else { return }

        let current = Double(display) ?? 0
        guard let result = evaluate(prev, op, current) else {
            display = "Error"
            expression = ""
            previousOperand = nil
            currentOperator = nil
            waitingForOperand = false
            return
        }

        let entry = formatHistoryEntry(prev, op, current, result)
        history.append(entry)

        display = String(result)
        expression = ""
        previousOperand = nil
        currentOperator = nil
        waitingForOperand = false
    }

    func handleClear() {
        display = "0"
        previousOperand = nil
        waitingForOperand = false
        expression = ""
    }

    func handleBackspace() {
        if waitingForOperand || display == "Error" { return }
        let result = String(display.dropLast())
        display = result
    }

    func handlePercent() {
        let current = Double(display) ?? 0
        let result = percent(current)
        display = String(result)
    }

    func handleMemoryAdd() {
        memory += Double(display) ?? 0
    }

    func handleMemorySub() {
        memory -= Double(display) ?? 0
    }

    func handleMemoryRecall() {
        display = "memory"
        waitingForOperand = false
    }

    func handleMemoryClear() {
        memory = 0
    }

    func handleButton(_ action: String) {
        if display == "Error" && action != "clear" {
            return
        }

        switch action {
        case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9":
            handleDigit(action)
        case ".":
            handleDecimal()
        case "+", "-", "*", "/":
            handleOperator(action)
        case "=":
            handleEquals()
        case "clear":
            handleClear()
        case "backspace":
            handleBackspace()
        case "percent":
            handlePercent()
        case "memory-add":
            handleMemoryAdd()
        case "memory-sub":
            handleMemorySub()
        case "memory-recall":
            handleMemoryRecall()
        case "memory-clear":
            handleMemoryClear()
        default:
            break
        }
    }
}
