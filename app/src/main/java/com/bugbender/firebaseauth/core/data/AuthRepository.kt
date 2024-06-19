package com.bugbender.firebaseauth.core.data


import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.bugbender.firebaseauth.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface AuthRepository {

    fun isUserLogged(): Boolean

    suspend fun login(email: String, password: String): AuthResult

    suspend fun loginWithGoogle(context: Context): AuthResult

    suspend fun signup(email: String, password: String): AuthResult

    fun userEmail(): String

    fun signOut()

    class Base @Inject constructor(
        private var auth: FirebaseAuth,
        private val provideResources: ProvideResources,
    ) : AuthRepository {

        override fun isUserLogged() = auth.currentUser != null

        override suspend fun login(email: String, password: String): AuthResult {
            return suspendCoroutine { cont ->
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        cont.resume(AuthResult.Success)
                    } else {
                        cont.resume(
                            AuthResult.Error(
                                message = task.exception?.message ?: "Authentication failed"
                            )
                        )
                    }
                }
            }
        }

        override suspend fun loginWithGoogle(context: Context): AuthResult {
            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(provideResources.stringById(R.string.web_client_id))
                .setAutoSelectEnabled(true)
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credentialManager = CredentialManager.create(context)

            return try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                val credential = result.credential

                when (credential) {
                    is CustomCredential -> {
                        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)

                            val authCredential = GoogleAuthProvider.getCredential(
                                googleIdTokenCredential.idToken,
                                null
                            )

                            // authenticate on server.
                            val authResult = auth.signInWithCredential(authCredential).await()

                            // authResult.user!!.updatePassword("password228") // todo if user has already signed
                            AuthResult.Success

                        } else {
                            AuthResult.Error(message = "Unexpected type of credential")
                        }
                    }

                    else -> {
                        AuthResult.Error(message = "Unexpected type of credential")
                    }
                }
            } catch (e: GetCredentialException) {
                AuthResult.Error(message = e.message ?: "Something went wrong")
            }
        }

        override suspend fun signup(email: String, password: String): AuthResult {
            return suspendCoroutine { cont ->
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        cont.resume(AuthResult.Success)
                    } else {
                        cont.resume(
                            AuthResult.Error(
                                message = task.exception?.message ?: "Can't create an account"
                            )
                        )
                    }
                }
            }
        }

        override fun userEmail() = auth.currentUser!!.email!!

        override fun signOut() = auth.signOut()
    }
}