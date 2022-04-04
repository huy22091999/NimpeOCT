package com.globits.nimpe.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule{
    @Binds
    fun bindViewModelFactory(factory: NimpeViewModelFactory): ViewModelProvider.Factory
}