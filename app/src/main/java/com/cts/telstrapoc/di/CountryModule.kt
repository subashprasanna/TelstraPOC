package com.cts.telstrapoc.di

import androidx.lifecycle.ViewModelProvider
import com.cts. telstrapoc.viewmodel.CountryViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class CountryModule {
    @Binds
    abstract fun bindCountryViewModelFactory(
        factory: CountryViewModelFactory
    ) : ViewModelProvider.Factory
}