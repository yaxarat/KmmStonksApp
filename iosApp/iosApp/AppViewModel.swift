//
//  AppViewModel.swift
//  iosApp
//
//  Created by Yashar Atajan on 4/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class AppViewModel: ObservableObject {
    
    @Published var appState: AppState
    
    private let coreModel: KMPViewModel = KMPViewModel.Factory().getIosInstance()
    
    var event: Events {
        return coreModel.events
    }
    
    var stateProvider: StateProvider {
        return appState.getStateProvider(model: coreModel)
    }
    
    init() {
        // "getDefaultAppState" and "onChange" are iOS-only KMPViewModel's extension functions, defined in shared/iosMain
        
        appState = coreModel.getDefaultAppState()
        
        coreModel.onChange { newState in
            self.appState = newState
        }
    }
}
