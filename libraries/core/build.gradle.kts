plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.libraries.core"
    compileSdk = ConfigData.compileSdkVersion
}

dependencies {
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
}
