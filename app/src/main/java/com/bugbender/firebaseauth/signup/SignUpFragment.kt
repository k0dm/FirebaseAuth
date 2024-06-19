package com.bugbender.firebaseauth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bugbender.firebaseauth.R
import com.bugbender.firebaseauth.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton.setOnClickListener {
            if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Email and password mustn't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.createAccount(
                    email = emailEditText.text.toString(),
                    password = passwordEditText.text.toString()
                )
            }
        }

        viewModel.liveData().observe(viewLifecycleOwner) { uiState ->
            uiState.show(binding)
        }
    }
}