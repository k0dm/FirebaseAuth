package com.bugbender.firebaseauth.profile

import androidx.lifecycle.ViewModel
import com.bugbender.firebaseauth.core.Navigation
import com.bugbender.firebaseauth.signin.SignInScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val navigation: Navigation.Update
) : ViewModel() {

    private lateinit var auth: FirebaseAuth

    fun init() {
        auth = Firebase.auth
    }

    fun userLogin() = auth.currentUser!!.email

    fun logOut() {
        auth.signOut()
        navigation.updateUi(SignInScreen)
    }
}