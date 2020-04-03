package com.cts.telstrapoc.di

import com.cts.telstrapoc.ui.CountryFragment
import dagger.Component

@Component(modules = [CountryModule::class, APIModule::class])
interface DIComponent {
    fun inject(fragment: CountryFragment)
}