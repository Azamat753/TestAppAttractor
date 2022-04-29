package com.example.testappattractor.di

import com.example.testappattractor.data.GreetingData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGreetingData(): GreetingData {
        return GreetingData()
    }

}