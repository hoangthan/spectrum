plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}


android {
    namespace = "com.spectrum.libraries.persistence"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    implementation(Deps.roomKtx)
    implementation(Deps.roomRuntime)
    kapt(Deps.roomCompiler)
}