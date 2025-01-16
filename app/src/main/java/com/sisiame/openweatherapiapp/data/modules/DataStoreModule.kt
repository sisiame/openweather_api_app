package com.sisiame.openweatherapiapp.data.modules

import android.content.Context
import com.sisiame.openweatherapiapp.data.local.WeatherDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideWeatherDataStore(context: Context): WeatherDataStore {
        return WeatherDataStore(context)
    }
}