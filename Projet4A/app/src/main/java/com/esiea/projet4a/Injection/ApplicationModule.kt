package com.esiea.projet4a.Injection

import com.esiea.projet4a.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory{ MainViewModel()}
}


