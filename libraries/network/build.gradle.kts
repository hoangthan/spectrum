plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.libraries.core"
    compileSdk = ConfigData.compileSdkVersion

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(Deps.retrofitRuntime)
    api(Deps.retrofitMoshi)
    api(Deps.moshiAdapter)
    api(Deps.moshiKotlin)
    api(Deps.okhttpLogging)
    api(Deps.okhttpCore)
    api(Deps.sandwich)
    api(Deps.hiltAndroid)

    kapt(Deps.moshiCodegen)
    kapt(Deps.hiltCompiler)
}
