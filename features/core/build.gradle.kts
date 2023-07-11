plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.spectrum.features.core"
    compileSdk = ConfigData.compileSdkVersion

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
}
