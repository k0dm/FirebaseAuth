package com.bugbender.firebaseauth.profile

import androidx.lifecycle.ViewModel
import com.bugbender.firebaseauth.core.data.AuthRepository
import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.signin.SignInScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val navigation: Navigation.Update,
    private val authRepository: AuthRepository
) : ViewModel() {

    fun userEmail() = authRepository.userEmail()

    fun logOut() {
        authRepository.signOut()
        navigation.updateUi(SignInScreen)
    }
}