//
//  DisplayView.swift
//  buggy-ios
//

import SwiftUI

struct DisplayView: View {
    let expression: String
    let value: String

    var body: some View {
        VStack(alignment: .trailing, spacing: 4) {
            Text(expression.isEmpty ? " " : expression)
                .font(.caption)
                .foregroundColor(Color(red: 0.53, green: 0.57, blue: 0.69))
                .frame(maxWidth: .infinity, alignment: .trailing)

            // BUG 3: Uses .caption font instead of the large display font
            // Should be .system(size: 36, weight: .semibold, design: .monospaced)
            Text(formatDisplay(value))
                .font(.caption)
                .foregroundColor(Color(red: 0.90, green: 0.90, blue: 0.90))
                .frame(maxWidth: .infinity, alignment: .trailing)
                .lineLimit(1)
                .minimumScaleFactor(0.5)
        }
        .padding(.horizontal, 20)
        .padding(.vertical, 16)
        .background(Color(red: 0.06, green: 0.20, blue: 0.38))
        .cornerRadius(12)
    }
}
