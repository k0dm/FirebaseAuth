package com.bugbender.firebaseauth.main

import androidx.lifecycle.ViewModel
import com.bugbender.firebaseauth.core.data.AuthRepository
import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.core.presentation.ProvideLiveData
import com.bugbender.firebaseauth.core.presentation.Screen
import com.bugbender.firebaseauth.profile.UserProfileScreen
import com.bugbender.firebaseauth.signin.SignInScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable,
    private val authRepository: AuthRepository
) : ViewModel(), ProvideLiveData<Screen> {

    fun checkUserSigning() {
        val screen = if (authRepository.isUserLogged()) {
            UserProfileScreen
        } else {
            SignInScreen
        }
        navigation.updateUi(screen)
    }

    override fun liveData() = navigation.liveData()
}