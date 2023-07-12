plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.feature.movie"
    compileSdk = ConfigData.compileSdkVersion

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":features:core"))
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
}
