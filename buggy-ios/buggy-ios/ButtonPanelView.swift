//
//  ButtonPanelView.swift
//  buggy-ios
//

import SwiftUI

struct CalcButton {
    let label: String
    let type: String
    let action: String
    var wide: Bool = false
}

private let buttons: [CalcButton] = [
    CalcButton(label: "MC", type: "memory", action: "memory-clear"),
    CalcButton(label: "MR", type: "memory", action: "memory-recall"),
    CalcButton(label: "M+", type: "memory", action: "memory-add"),
    CalcButton(label: "M\u{2212}", type: "memory", action: "memory-sub"),

    CalcButton(label: "C", type: "clear", action: "clear"),
    CalcButton(label: "\u{232b}", type: "clear", action: "backspace"),
    CalcButton(label: "%", type: "operator", action: "percent"),
    CalcButton(label: "\u{00f7}", type: "operator", action: "/"),

    CalcButton(label: "7", type: "digit", action: "7"),
    CalcButton(label: "8", type: "digit", action: "8"),
    CalcButton(label: "9", type: "digit", action: "9"),
    CalcButton(label: "\u{00d7}", type: "operator", action: "*"),

    CalcButton(label: "4", type: "digit", action: "4"),
    CalcButton(label: "5", type: "digit", action: "5"),
    CalcButton(label: "6", type: "digit", action: "6"),
    CalcButton(label: "\u{2212}", type: "operator", action: "-"),

    CalcButton(label: "1", type: "digit", action: "1"),
    CalcButton(label: "2", type: "digit", action: "2"),
    CalcButton(label: "3", type: "digit", action: "3"),
    CalcButton(label: "+", type: "operator", action: "+"),

    CalcButton(label: "0", type: "digit", action: "0", wide: true),
    CalcButton(label: ".", type: "digit", action: "."),
    CalcButton(label: "=", type: "equals", action: "="),
]

struct ButtonPanelView: View {
    let onButton: (String) -> Void

    private let columns = Array(repeating: GridItem(.flexible(), spacing: 8), count: 4)

    var body: some View {
        LazyVGrid(columns: columns, spacing: 8) {
            ForEach(Array(buttons.enumerated()), id: \.offset) { _, btn in
                if btn.wide {
                    Button(action: { onButton(btn.action) }) {
                        Text(btn.label)
                            .font(.system(size: btn.type == "memory" ? 14 : 18, weight: .medium))
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 16)
                            .background(backgroundColor(for: btn.type))
                            .foregroundColor(foregroundColor(for: btn.type))
                            .cornerRadius(10)
                    }
                    .gridCellColumns(2)
                } else {
                    Button(action: { onButton(btn.action) }) {
                        Text(btn.label)
                            .font(.system(size: btn.type == "memory" ? 14 : 18, weight: .medium))
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, btn.type == "memory" ? 10 : 16)
                            .background(backgroundColor(for: btn.type))
                            .foregroundColor(foregroundColor(for: btn.type))
                            .cornerRadius(10)
                    }
                }
            }
        }
    }

    private func backgroundColor(for type: String) -> Color {
        switch type {
        case "digit": return Color(red: 0.10, green: 0.10, blue: 0.24)
        case "operator": return Color(red: 0.91, green: 0.27, blue: 0.37)
        case "equals": return Color(red: 0.06, green: 0.61, blue: 0.34)
        case "clear": return Color(red: 0.33, green: 0.20, blue: 0.51)
        case "memory": return Color(red: 0.10, green: 0.10, blue: 0.24)
        default: return Color.gray
        }
    }

    private func foregroundColor(for type: String) -> Color {
        switch type {
        case "digit": return Color(red: 0.90, green: 0.90, blue: 0.90)
        case "memory": return Color(red: 0.53, green: 0.57, blue: 0.69)
        default: return .white
        }
    }
}
