package com.bugbender.firebaseauth.signup

import com.bugbender.firebaseauth.core.presentation.LiveDataWrapper
import javax.inject.Inject

interface SignUpLiveDataWrapper: LiveDataWrapper<SignUpUiState> {

    class Base @Inject constructor(): SignUpLiveDataWrapper, LiveDataWrapper.Base<SignUpUiState>()
}