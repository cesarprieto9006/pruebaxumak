package com.example.pruebaxumak.di

import android.content.Context
import com.example.pruebaxumak.database.AppDatabase
import com.example.pruebaxumak.remote.DataApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.breakingbadapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideDataApi(retrofit: Retrofit): DataApi = retrofit.create(DataApi::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.dataDao()

}