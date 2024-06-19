package com.bugbender.firebaseauth.di

import com.bugbender.firebaseauth.signup.SignUpAuthResultMapper
import com.bugbender.firebaseauth.signup.SignUpLiveDataWrapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SignUpModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLiveDataWrapper(liveDataWrapper: SignUpLiveDataWrapper.Base): SignUpLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindMapper(mapper: SignUpAuthResultMapper.Base): SignUpAuthResultMapper
}