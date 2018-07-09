package com.vagnnermartins.baseproject.di

import com.vagnnermartins.baseproject.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app
}
