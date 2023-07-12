plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.feature.movie"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    api(Deps.androidKtx)
    api(Deps.appCompat)
    api(Deps.materialUi)
    api(Deps.constraintLayout)
    api(Deps.viewPager)
    api(Deps.pagingRuntime)
    api(Deps.coil)
    api(Deps.navigationUi)
    api(Deps.navigationFragment)
    api(Deps.lifecycleRuntime)
    api(Deps.lifecycleViewModel)
    api(Deps.viewmodelViewModelState)

    implementation(project(":features:core"))
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
}
