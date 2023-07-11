plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.libraries.core"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(Deps.retrofitRuntime)
    api(Deps.retrofitMoshi)
    api(Deps.moshiCodegen)
    api(Deps.moshiAdapter)
    api(Deps.moshiCore)
    api(Deps.moshiKotlin)
    api(Deps.okhttpLogging)
    api(Deps.okhttpCore)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
}
