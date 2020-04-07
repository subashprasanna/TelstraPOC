package com.cts.telstrapoc.di

import com.cts.telstrapoc.model.network.Api
import dagger.Module
import dagger.Provides

@Module
public class APIModule {
    @Provides
    public fun providesAPI() : Api {
        return Api()
    }
}