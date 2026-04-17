//
//  HistoryPanelView.swift
//  buggy-ios
//

import SwiftUI

struct HistoryPanelView: View {
    let history: [String]

    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            Text("HISTORY")
                .font(.system(size: 12, weight: .semibold))
                .foregroundColor(Color(red: 0.53, green: 0.57, blue: 0.69))
                .kerning(1)

            ScrollView {
                LazyVStack(alignment: .leading, spacing: 6) {
                    if history.isEmpty {
                        Text("No calculations yet")
                            .font(.system(size: 13))
                            .foregroundColor(Color(red: 0.29, green: 0.33, blue: 0.41))
                            .italic()
                    }
                    // BUG 7: History shows oldest first instead of newest first
                    // Should be: ForEach(Array(history.reversed().enumerated()), ...)
                    ForEach(Array(history.enumerated()), id: \.offset) { _, entry in
                        Text(entry)
                            .font(.system(size: 13, design: .monospaced))
                            .foregroundColor(Color(red: 0.75, green: 0.78, blue: 0.85))
                            .padding(.horizontal, 8)
                            .padding(.vertical, 6)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .background(Color.white.opacity(0.03))
                            .cornerRadius(6)
                    }
                }
            }
        }
        .padding(16)
        .background(Color(red: 0.06, green: 0.20, blue: 0.38))
        .cornerRadius(12)
    }
}
