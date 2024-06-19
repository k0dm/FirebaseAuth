package com.bugbender.firebaseauth.di

import com.bugbender.firebaseauth.core.Navigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun mutableNavigation(navigation: Navigation.Base): Navigation.Mutable

    @Binds
    abstract fun updateNavigation(navigation: Navigation.Base): Navigation.Update
}