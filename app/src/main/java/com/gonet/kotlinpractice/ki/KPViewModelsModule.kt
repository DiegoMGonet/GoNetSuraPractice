package com.gonet.kotlinpractice.ki

import com.gonet.kotlinpractice.flows.cart.viewModels.KPCartViewModel
import com.gonet.kotlinpractice.flows.description.viewmodels.KPSummaryViewModel
import com.gonet.kotlinpractice.flows.home.viewmodels.KPHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { KPHomeViewModel(get(), get()) }
    viewModel { KPSummaryViewModel(get()) }
    viewModel { KPCartViewModel(get()) }
}