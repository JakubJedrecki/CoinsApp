package com.jakub.coinsapp.di

import com.jakub.data.repositories.CoinsRepositoryImpl
import com.jakub.domain.repositories.CoinsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinsRepository(coinsRepositoryImpl: CoinsRepositoryImpl): CoinsRepository
}