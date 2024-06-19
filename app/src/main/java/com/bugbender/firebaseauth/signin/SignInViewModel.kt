package com.bugbender.firebaseauth.signin

import com.bugbender.firebaseauth.core.data.AuthRepository
import com.bugbender.firebaseauth.core.presentation.BaseViewModel
import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.core.presentation.ProvideLiveData
import com.bugbender.firebaseauth.core.presentation.RunAsync
import com.bugbender.firebaseauth.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val navigation: Navigation.Update,
    private val authRepository: AuthRepository,
    private val mapper: SignInAuthResultMapper,
    private val signInLiveDataWrapper: SignInLiveDataWrapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<SignInUiState> {

    fun login(email: String, password: String) {
        signInLiveDataWrapper.updateUi(SignInUiState.Progress)

        runAsync({
            authRepository.login(email = email, password = password)
        }) { authResult ->
            authResult.map(mapper)
        }
    }

    fun createAccount() {
        navigation.updateUi(SignUpScreen)
    }

    override fun liveData() = signInLiveDataWrapper.liveData()
}