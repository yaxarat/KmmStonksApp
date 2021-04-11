//
//  LoadingComponent.swift
//  iosApp
//
//  Created by Yashar Atajan on 4/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LoadingComponent: View {
    var body: some View {
        VStack {
            Spacer()
            Text("loading...")
            Spacer().frame(height: 30)
            ProgressView().progressViewStyle(CircularProgressViewStyle())
            Spacer()
        }
    }
}
