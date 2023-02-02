package com.gonet.kotlinpractice.services.ki

import com.gonet.kotlinpractice.flows.home.repositories.KPProductsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory {
        KPProductsRepository(get())
    }
}