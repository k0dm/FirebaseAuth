package com.bugbender.firebaseauth.signup

import com.bugbender.firebaseauth.core.data.AuthRepository
import com.bugbender.firebaseauth.core.presentation.BaseViewModel
import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.core.presentation.ProvideLiveData
import com.bugbender.firebaseauth.core.presentation.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: SignUpAuthResultMapper,
    private val singUpLiveDataWrapper: SignUpLiveDataWrapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<SignUpUiState> {

    fun createAccount(email: String, password: String) {
        singUpLiveDataWrapper.updateUi(SignUpUiState.Progress)

        runAsync({
            authRepository.signup(email = email, password = password)
        }) { authResult ->
            authResult.map(mapper)
        }
    }

    override fun liveData() = singUpLiveDataWrapper.liveData()
}