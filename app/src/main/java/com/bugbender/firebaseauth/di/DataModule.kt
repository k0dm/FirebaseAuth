package com.bugbender.firebaseauth.di

import com.bugbender.firebaseauth.core.data.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(repository: AuthRepository.Base): AuthRepository

    companion object {

        @Provides
        fun provideAuth(): FirebaseAuth = Firebase.auth
    }
}