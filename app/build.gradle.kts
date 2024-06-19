plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.bugbender.firebaseauth"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bugbender.firebaseauth"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //Firebase and auth
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)  //Firebase Authentication library
    implementation(libs.play.services.auth) // Google Play services library for auth

    //Google auth
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)     //for credentials support from play services, for devices running  Android 13 and below.
    implementation(libs.googleid)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //viewModel by viewModels()
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    //Base libs
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}