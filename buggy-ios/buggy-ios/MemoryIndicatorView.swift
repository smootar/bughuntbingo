//
//  MemoryIndicatorView.swift
//  buggy-ios
//

import SwiftUI

struct MemoryIndicatorView: View {
    let hasMemory: Bool

    var body: some View {
        Text(hasMemory ? "M" : " ")
            .font(.system(size: 14, weight: .semibold))
            .foregroundColor(Color(red: 0.91, green: 0.27, blue: 0.37))
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.leading, 4)
    }
}
