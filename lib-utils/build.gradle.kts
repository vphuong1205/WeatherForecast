import extension.addTestsDependencies
import extension.addAndroidTestsDependencies
import extension.implementation
import extension.kapt

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_ANDROID)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOL_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK)
        versionCode(BuildAndroidConfig.VERSION_CODE)
        versionName(BuildAndroidConfig.VERSION_NAME)
        testInstrumentationRunner(BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER)
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Androidx.CORE_KTX)
    implementation(Dependencies.Kotlin.KOTLIN_COROUTINES_CORE)

    implementation(Dependencies.DaggerHilt.HILT_ANDROID)
    kapt(Dependencies.DaggerHilt.HILT_ANDROID_COMPILER)

    implementation(TestDependencies.Junit.KOTLIN_TEST)
    implementation(TestDependencies.Jupiter.JUNIT5_API)
    addTestsDependencies()
    addAndroidTestsDependencies()
}
