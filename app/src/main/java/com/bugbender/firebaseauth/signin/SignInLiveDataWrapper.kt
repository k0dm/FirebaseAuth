package com.bugbender.firebaseauth.signin

import com.bugbender.firebaseauth.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface SignInLiveDataWrapper: LiveDataWrapper<SignInUiState> {

    class Base @Inject constructor(): SignInLiveDataWrapper, LiveDataWrapper.Base<SignInUiState>()
}