package com.bugbender.firebaseauth.di

import com.bugbender.firebaseauth.signin.SignInLiveDataWrapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SignInModule {

    @Binds
    @ViewModelScoped
    abstract fun liveDataWrapper(liveDataWrapper: SignInLiveDataWrapper.Base): SignInLiveDataWrapper
}

