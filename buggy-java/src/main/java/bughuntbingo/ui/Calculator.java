package bughuntbingo.ui;

import bughuntbingo.utils.Calculate;
import bughuntbingo.utils.Format;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator extends JPanel {

    private String display = "0";
    private Double previousOperand = null;
    private String currentOperator = null;
    private boolean waitingForOperand = false;
    private double memory = 0;
    private final List<String> history = new ArrayList<>();
    private String expression = "";

    private final MemoryIndicator memoryIndicator;
    private final Display displayPanel;
    private final ButtonPanel buttonPanel;
    private final HistoryPanel historyPanel;

    public Calculator() {
        memoryIndicator = new MemoryIndicator();
        displayPanel = new Display();
        buttonPanel = new ButtonPanel(this::handleButton);
        historyPanel = new HistoryPanel();

        setLayout(new GridBagLayout());
        setBackground(new Color(0x16213e));
        setBorder(new EmptyBorder(24, 24, 24, 24));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL; c.insets = new Insets(0, 0, 12, 0);
        add(memoryIndicator, c);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE; c.insets = new Insets(0, 0, 12, 12);
        add(displayPanel, c);

        c.gridx = 1; c.gridy = 1; c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH; c.weighty = 1; c.insets = new Insets(0, 0, 0, 0);
        add(historyPanel, c);

        c.gridx = 0; c.gridy = 2; c.gridheight = 1;
        c.fill = GridBagConstraints.NONE; c.weighty = 0; c.insets = new Insets(0, 0, 0, 12);
        add(buttonPanel, c);
    }

    private void handleDigit(String digit) {
        if (waitingForOperand) {
            display = digit;
            waitingForOperand = false;
        } else {
            display = display.equals("0") ? digit : display + digit;
        }
        refreshDisplay();
    }

    private void handleDecimal() {
        if (waitingForOperand) {
            display = "0.";
            waitingForOperand = false;
            refreshDisplay();
            return;
        }
        display = display + ".";
        refreshDisplay();
    }

    private void handleOperator(String nextOperator) {
        double current = parseDisplay();

        previousOperand = current;

        currentOperator = nextOperator;
        waitingForOperand = true;
        expression = formatNumber(current) + " " + nextOperator;
        refreshAll();
    }

    private void handleEquals() {
        if (previousOperand == null || currentOperator == null) return;

        double current = parseDisplay();
        Object result = Calculate.evaluate(previousOperand, currentOperator, current);

        history.add(Format.formatHistoryEntry(previousOperand, currentOperator, current, result));

        display = result instanceof String ? (String) result : formatNumber(((Number) result).doubleValue());
        expression = "";
        previousOperand = null;
        currentOperator = null;
        waitingForOperand = false;
        refreshAll();
    }

    private void handleClear() {
        display = "0";
        previousOperand = null;
        waitingForOperand = false;
        expression = "";
        refreshAll();
    }

    private void handleBackspace() {
        if (waitingForOperand || display.equals("Error")) return;
        String result = display.substring(0, display.length() - 1);
        display = result;
        refreshDisplay();
    }

    private void handlePercent() {
        display = formatNumber(Calculate.percent(parseDisplay()));
        refreshDisplay();
    }

    private void handleMemoryAdd() {
        memory += parseDisplay();
        refreshMemory();
    }

    private void handleMemorySub() {
        memory -= parseDisplay();
        refreshMemory();
    }

    private void handleMemoryRecall() {
        display = "memory";
        waitingForOperand = false;
        refreshDisplay();
    }

    private void handleMemoryClear() {
        memory = 0;
        refreshMemory();
    }

    private void handleButton(String action) {
        if (display.equals("Error") && !action.equals("clear")) return;

        switch (action) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                handleDigit(action); break;
            case ".":           handleDecimal(); break;
            case "+": case "-": case "*": case "/":
                handleOperator(action); break;
            case "=":           handleEquals(); break;
            case "clear":       handleClear(); break;
            case "backspace":   handleBackspace(); break;
            case "percent":     handlePercent(); break;
            case "memory-add":  handleMemoryAdd(); break;
            case "memory-sub":  handleMemorySub(); break;
            case "memory-recall": handleMemoryRecall(); break;
            case "memory-clear":  handleMemoryClear(); break;
        }
    }

    private void refreshDisplay() { displayPanel.update(expression, display); }
    private void refreshMemory()  { memoryIndicator.update(memory != 0); }
    private void refreshHistory() { historyPanel.update(history); }
    private void refreshAll()     { refreshDisplay(); refreshMemory(); refreshHistory(); }

    private double parseDisplay() {
        if (display == null || display.isEmpty()) return Double.NaN;
        try { return Double.parseDouble(display); }
        catch (NumberFormatException e) { return Double.NaN; }
    }

    private String formatNumber(double v) {
        if (v % 1 == 0 && !Double.isInfinite(v) && !Double.isNaN(v))
            return String.valueOf((long) v);
        return String.valueOf(v);
    }
}
