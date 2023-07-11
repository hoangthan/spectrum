plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.spectrum.libraries.core"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

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
