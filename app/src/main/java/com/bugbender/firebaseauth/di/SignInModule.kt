package com.bugbender.firebaseauth.di

import com.bugbender.firebaseauth.signin.SignInAuthResultMapper
import com.bugbender.firebaseauth.signin.SignInLiveDataWrapper
import com.bugbender.firebaseauth.signup.SignUpLiveDataWrapper
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
    abstract fun bindLiveDataWrapper(liveDataWrapper: SignInLiveDataWrapper.Base): SignInLiveDataWrapper

    @Binds
    @ViewModelScoped
    abstract fun bindMapper(mapper: SignInAuthResultMapper.Base): SignInAuthResultMapper
}