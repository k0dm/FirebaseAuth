package com.bugbender.firebaseauth.di

import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.core.presentation.RunAsync
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindMutableNavigation(navigation: Navigation.Base): Navigation.Mutable

    @Binds
    abstract fun bindUpdateNavigation(navigation: Navigation.Base): Navigation.Update

    @Binds
    abstract fun bindRunAsync(runAsync: RunAsync.Base): RunAsync
}