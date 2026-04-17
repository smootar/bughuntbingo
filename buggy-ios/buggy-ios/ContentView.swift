//
//  ContentView.swift
//  buggy-ios
//

import SwiftUI

struct ContentView: View {
    @State private var calculator = CalculatorModel()

    var body: some View {
        ZStack {
            Color(red: 0.10, green: 0.10, blue: 0.18)
                .ignoresSafeArea()

            VStack(spacing: 12) {
                MemoryIndicatorView(hasMemory: calculator.memory != 0)
                DisplayView(expression: calculator.expression, value: calculator.display)
                ButtonPanelView { action in
                    calculator.handleButton(action)
                }
                HistoryPanelView(history: calculator.history)
                    .frame(maxHeight: 160)
            }
            .frame(width: 300)
            .padding(24)
            .background(Color(red: 0.09, green: 0.13, blue: 0.24))
            .cornerRadius(16)
            .shadow(color: .black.opacity(0.5), radius: 30, y: 10)
            .padding(20)
        }
    }
}

#Preview {
    ContentView()
}
