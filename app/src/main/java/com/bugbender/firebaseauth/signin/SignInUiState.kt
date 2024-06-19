package com.bugbender.firebaseauth.signin

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bugbender.firebaseauth.R
import com.bugbender.firebaseauth.databinding.FragmentSignInBinding

interface SignInUiState {

    fun show(binding: FragmentSignInBinding)

    object Progress : SignInUiState {

        override fun show(binding: FragmentSignInBinding) = with(binding) {
            loginButton.isEnabled = false
            loginButton.setBackgroundColor(
                ContextCompat.getColor(
                    loginButton.context,
                    R.color.gray
                )
            )
        }
    }

    data class Error(private val message: String) : SignInUiState {

        override fun show(binding: FragmentSignInBinding) = with(binding) {
            loginButton.isEnabled = true
            loginButton.setBackgroundColor(
                ContextCompat.getColor(
                    loginButton.context,
                    R.color.black
                )
            )
            Toast.makeText(
                loginButton.context,
                message,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}