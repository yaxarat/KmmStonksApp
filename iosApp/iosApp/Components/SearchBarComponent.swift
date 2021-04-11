//
//  SearchBarComponent.swift
//  iosApp
//
//  Created by Yashar Atajan on 4/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SearchBar: View {
    @State private var newWord = ""
    let addStock: (String) -> Void
    
    var body: some View {
        HStack{
            Image(systemName: "magnifyingglass")
            TextField("Search new stock", text: $newWord, onCommit: {
                addStock(newWord)
                newWord = ""
            })
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .padding()
            .autocapitalization(.allCharacters)
        }
        .padding(.leading, 8)
        .padding(.trailing, 8)
        .padding(.top, 12)
    }
}
