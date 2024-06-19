package com.bugbender.firebaseauth.signin

import com.bugbender.firebaseauth.core.data.AuthResult
import com.bugbender.firebaseauth.core.presentation.Navigation
import com.bugbender.firebaseauth.profile.UserProfileScreen
import com.bugbender.firebaseauth.signup.SignUpAuthResultMapper
import javax.inject.Inject

interface SignInAuthResultMapper : AuthResult.Mapper {

    class Base @Inject constructor(
        private val navigation: Navigation.Update,
        private val liveDataWrapper: SignInLiveDataWrapper
    ) : SignInAuthResultMapper {

        override fun mapSuccess() {
            navigation.updateUi(UserProfileScreen)
        }

        override fun mapError(message: String) {
            liveDataWrapper.updateUi(SignInUiState.Error(message = message))
        }
    }
}