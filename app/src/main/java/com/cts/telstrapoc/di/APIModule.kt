package com.cts.telstrapoc.di

import com.cts.telstrapoc.model.network.Api
import dagger.Module
import dagger.Provides

@Module
class APIModule {
    @Provides
    fun providesAPI() : Api {
        return Api()
    }
}