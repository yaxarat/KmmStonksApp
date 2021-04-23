//
//  StockListScreen.swift
//  iosApp
//
//  Created by Yashar Atajan on 4/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StockListScreen: View {
    @EnvironmentObject var viewModel: AppViewModel
    
    var body: some View {
        let stockListState = viewModel.stateProvider.getStockListState()
        let event = viewModel.event
        
        if stockListState.isLoading {
            LoadingComponent()
        } else {
            VStack {
                SearchBar() { ticker in
                    event.showLoadingIndicator()
                    event.insertTicker(tickerSymbol: ticker)
                }
                
                if stockListState.stockListItems.isEmpty {
                    Text("No stock available, tap here to add a bunch")
                        .padding()
                        .onTapGesture {
                            event.showLoadingIndicator()
                            event.insertTickers(tickerSymbols: ["MSFT", "AAPL", "TSLA", "COF", "BA", "V", "AMZN", "GOOG", "LYFT", "FB", "GME"])
                        }
                } else {
                    ScrollView {
                        ForEach(stockListState.stockListItems, id: \.self) { stock in
                            StockRow(stock: stock)
                                .onTapGesture {
                                    print("Tapped \(stock.name)")
                                }
                                .applyCardStyle()
                                .padding(.top, 10)
                                .padding(.leading, 20)
                                .padding(.trailing, 20)
                        }
                    }
                }
            }
        }
    }
}
