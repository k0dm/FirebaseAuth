package com.bugbender.firebaseauth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bugbender.firebaseauth.R
import com.bugbender.firebaseauth.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding get() = _binding!!
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        createAccountTextView.setOnClickListener {
            viewModel.createAccount()
        }

        loginButton.setOnClickListener {
            if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Email and password mustn't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.login(
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