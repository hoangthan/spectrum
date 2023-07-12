plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.libraries.movie"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        val apiVersion = 3
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/${apiVersion}/\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":libraries:core"))
    implementation(project(":libraries:network"))

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    kapt(Deps.moshiCodegen)
}
