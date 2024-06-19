package com.bugbender.firebaseauth.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bugbender.firebaseauth.core.BaseViewModel
import com.bugbender.firebaseauth.core.Navigation
import com.bugbender.firebaseauth.core.ProvideLiveData
import com.bugbender.firebaseauth.core.RunAsync
import com.bugbender.firebaseauth.core.Screen
import com.bugbender.firebaseauth.profile.UserProfileScreen
import com.bugbender.firebaseauth.signup.SignUpScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val navigation: Navigation.Update,
    private val signInLiveDataWrapper: SignInLiveDataWrapper,
) : ViewModel(), ProvideLiveData<SignInUiState> {

    private lateinit var auth: FirebaseAuth

    fun init() {
        auth = Firebase.auth
    }

    fun login(email: String, password: String) {
        signInLiveDataWrapper.updateUi(SignInUiState.Progress)

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    navigation.updateUi(UserProfileScreen)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", )
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
                    signInLiveDataWrapper.updateUi(SignInUiState.Error(message = task.exception?.message.toString()))
                }
            }
    }

    fun createAccount() {
        navigation.updateUi(SignUpScreen)
    }

    override fun liveData() = signInLiveDataWrapper.liveData()
}