plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.features.core"
    compileSdk = ConfigData.compileSdkVersion

    buildFeatures {
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
    api(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
}
