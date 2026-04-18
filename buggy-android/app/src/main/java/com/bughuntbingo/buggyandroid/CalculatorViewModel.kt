package com.bughuntbingo.buggyandroid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var display by mutableStateOf("0")
        private set

    var previousOperand by mutableStateOf<Double?>(null)
        private set

    var currentOperator by mutableStateOf<String?>(null)
        private set

    var waitingForOperand by mutableStateOf(false)
        private set

    var memory by mutableStateOf(0.0)
        private set

    var history by mutableStateOf<List<String>>(emptyList())
        private set

    var expression by mutableStateOf("")
        private set

    fun handleDigit(digit: String) {
        if (waitingForOperand) {
            display = digit
            waitingForOperand = false
        } else {
            display = if (display == "0") digit else display + digit
        }
    }

    fun handleDecimal() {
        if (waitingForOperand) {
            display = "0."
            waitingForOperand = false
            return
        }
        display = display + "."
    }

    fun handleOperator(nextOperator: String) {
        val current = display.toDoubleOrNull() ?: 0.0

        previousOperand = current

        currentOperator = nextOperator
        waitingForOperand = true

        val prev = previousOperand
        val op = currentOperator
        if (prev != null && op != null && !waitingForOperand) {
            val result = evaluate(prev, op, current)
            if (result != null) {
                expression = "$result $nextOperator"
            }
        } else {
            expression = "$current $nextOperator"
        }
    }

    fun handleEquals() {
        val prev = previousOperand ?: return
        val op = currentOperator ?: return

        val current = display.toDoubleOrNull() ?: 0.0
        val result = evaluate(prev, op, current)

        if (result == null) {
            display = "Error"
            expression = ""
            previousOperand = null
            currentOperator = null
            waitingForOperand = false
            return
        }

        val entry = formatHistoryEntry(prev, op, current, result)
        history = history + entry

        display = result.toString()
        expression = ""
        previousOperand = null
        currentOperator = null
        waitingForOperand = false
    }

    fun handleClear() {
        display = "0"
        previousOperand = null
        waitingForOperand = false
        expression = ""
    }

    fun handleBackspace() {
        if (waitingForOperand || display == "Error") return
        val result = display.dropLast(1)
        display = result
    }

    fun handlePercent() {
        val current = display.toDoubleOrNull() ?: 0.0
        val result = percent(current)
        display = result.toString()
    }

    fun handleMemoryAdd() {
        memory += display.toDoubleOrNull() ?: 0.0
    }

    fun handleMemorySub() {
        memory -= display.toDoubleOrNull() ?: 0.0
    }

    fun handleMemoryRecall() {
        display = "memory"
        waitingForOperand = false
    }

    fun handleMemoryClear() {
        memory = 0.0
    }

    fun handleButton(action: String) {
        if (display == "Error" && action != "clear") {
            return
        }

        when (action) {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> handleDigit(action)
            "." -> handleDecimal()
            "+", "-", "*", "/" -> handleOperator(action)
            "=" -> handleEquals()
            "clear" -> handleClear()
            "backspace" -> handleBackspace()
            "percent" -> handlePercent()
            "memory-add" -> handleMemoryAdd()
            "memory-sub" -> handleMemorySub()
            "memory-recall" -> handleMemoryRecall()
            "memory-clear" -> handleMemoryClear()
        }
    }
}
