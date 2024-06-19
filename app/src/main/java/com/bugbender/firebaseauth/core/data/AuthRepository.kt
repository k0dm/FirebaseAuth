package com.bugbender.firebaseauth.core.data


import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface AuthRepository {

    fun isUserLogged(): Boolean

    suspend fun login(email: String, password: String): AuthResult

    suspend fun signup(email: String, password: String): AuthResult

    fun userEmail(): String

    fun signOut()

    class Base @Inject constructor(private var auth: FirebaseAuth) : AuthRepository {

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