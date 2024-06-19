package com.bugbender.firebaseauth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bugbender.firebaseauth.core.Navigation
import com.bugbender.firebaseauth.core.ProvideLiveData
import com.bugbender.firebaseauth.profile.UserProfileScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val navigation: Navigation.Update,
    private val singUpLiveDataWrapper: SignUpLiveDataWrapper
) : ViewModel(), ProvideLiveData<SignUpUiState> {

    private lateinit var auth: FirebaseAuth

    fun init() {
        auth = Firebase.auth
    }

    fun createAccount(email: String, password: String) {
        singUpLiveDataWrapper.updateUi(SignUpUiState.Progress)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    navigation.updateUi(UserProfileScreen)
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    updateUI(null)
                    singUpLiveDataWrapper.updateUi(SignUpUiState.Error(message = task.exception?.message.toString()))
                }
            }
    }

    override fun liveData() = singUpLiveDataWrapper.liveData()
}