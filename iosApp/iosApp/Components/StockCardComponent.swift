//
//  StockCardComponent.swift
//  iosApp
//
//  Created by Yashar Atajan on 4/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct CardViewModifier: ViewModifier {
    
    func body(content: Content) -> some View {
        content
            .padding()
            .background(Color.gray)
            .cornerRadius(10)
            .shadow(color: Color.gray, radius: 1.0)
    }
}

extension View {
    func applyCardStyle() -> some View {
        self.modifier(CardViewModifier())
    }
}
