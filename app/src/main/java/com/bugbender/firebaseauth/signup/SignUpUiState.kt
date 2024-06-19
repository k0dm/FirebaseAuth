package com.bugbender.firebaseauth.signup

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bugbender.firebaseauth.R
import com.bugbender.firebaseauth.databinding.FragmentSignUpBinding

interface SignUpUiState {

    fun show(binding: FragmentSignUpBinding)

    object Progress : SignUpUiState {

        override fun show(binding: FragmentSignUpBinding) = with(binding) {
            signUpButton.isEnabled = false
            signUpButton.setBackgroundColor(
                ContextCompat.getColor(
                    signUpButton.context,
                    R.color.gray
                )
            )
        }
    }

    data class Error(private val message: String) : SignUpUiState {

        override fun show(binding: FragmentSignUpBinding) = with(binding) {
            signUpButton.isEnabled = true
            signUpButton.setBackgroundColor(
                ContextCompat.getColor(
                    signUpButton.context,
                    R.color.green
                )
            )
            Toast.makeText(
                signUpButton.context,
                message,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}