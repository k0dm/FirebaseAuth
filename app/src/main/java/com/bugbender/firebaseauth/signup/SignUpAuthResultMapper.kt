package com.bugbender.firebaseauth.signup

import com.bugbender.firebaseauth.core.data.AuthResult
import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.profile.UserProfileScreen
import javax.inject.Inject

interface SignUpAuthResultMapper : AuthResult.Mapper {

    class Base @Inject constructor(
        private val navigation: Navigation.Update,
        private val liveDataWrapper: SignUpLiveDataWrapper
    ) : SignUpAuthResultMapper {

        override fun mapSuccess() {
            navigation.updateUi(UserProfileScreen)
        }

        override fun mapError(message: String) {
            liveDataWrapper.updateUi(SignUpUiState.Error(message = message))
        }
    }
}