package com.bugbender.firebaseauth.main

import androidx.lifecycle.ViewModel
import com.bugbender.firebaseauth.core.Navigation
import com.bugbender.firebaseauth.core.ProvideLiveData
import com.bugbender.firebaseauth.core.Screen
import com.bugbender.firebaseauth.profile.UserProfileScreen
import com.bugbender.firebaseauth.signin.SignInScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable
) : ViewModel(), ProvideLiveData<Screen> {

    private lateinit var auth: FirebaseAuth

    fun checkUserSigning() {
        auth = Firebase.auth
        val currentUser = auth.currentUser

        val screen = if (currentUser != null) {
            UserProfileScreen
        } else {
            SignInScreen
        }
        navigation.updateUi(screen)
    }

    override fun liveData() = navigation.liveData()
}