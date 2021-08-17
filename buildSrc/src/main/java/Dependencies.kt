@file:Suppress("unused")

import org.gradle.api.JavaVersion

object Config {

    const val application_id = "com.example.professionalhomework"
    const val compile_sdk = 30
    const val build_tools = "30.0.3"
    const val min_sdk = 21
    const val target_sdk = 30
    const val jvmTarget = "1.8"
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Versions {

    // Design
    const val appcompat = "1.3.1"
    const val material = "1.5.0-alpha01"
    const val ssu = "1.0.6"
    const val lottie = "4.0.0"
    const val constraint = "2.1.0"

    // Kotlin
    const val stdlib = "1.5.21"
    const val core = "1.6.0"
    const val coroutines = "1.5.1"
    const val viewModelKtx = "2.3.1"

    // Retrofit
    const val gson = "2.9.0"
    const val interceptor = "5.0.0-alpha.2"
    const val retrofit = "2.9.0"

    // Room
    const val room = "2.3.0"
    const val roomCompiler = "2.3.0"
    const val roomKtx = "2.3.0"
    const val runtime = "2.3.0"

    // Logging
    const val timber = "5.0.0"

    // Koin
    const val koinCore = "3.1.2"
    const val koinAndroid = "3.1.2"

    // Navigation
    const val navigation = "2.3.5"

    // Coil
    const val coil = "1.3.2"

    //Testing
    const val junit = "4.13.2"
    const val ext_junit = "1.1.3"
    const val espresso_core = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val sdp = "com.intuit.sdp:sdp-android:${Versions.ssu}"
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.ssu}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.stdlib}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.runtime}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroid}"
}

object Navigation {
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val navigationDynamicFeature =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
}

object Testing {
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.ext_junit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
}

object Utils {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}