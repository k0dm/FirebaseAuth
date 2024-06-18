package com.bugbender.firebaseauth.core

import androidx.lifecycle.LiveData

interface ProvideLiveData<T : Any> {

    fun liveData(): LiveData<T> = throw IllegalStateException("Don't use in Unit tests")
}