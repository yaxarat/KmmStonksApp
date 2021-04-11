//
//  StockRow.swift
//  iosApp
//
//  Created by Yashar Atajan on 4/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StockRow: View {
    
    let stock: Stock
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(stock.tickerSymbol.ticker).font(Font.subheadline).bold()
                Text(stock.name).font(Font.body)
            }
            Spacer()
            VStack(alignment: .trailing) {
                Text(String(format: "%.2f", stock.current)).font(Font.body)
                Text(String(format: "Previous close %.2f", stock.previousClose)).font(Font.caption).foregroundColor(.white)
            }
        }.frame(maxWidth: .infinity)
    }
}
