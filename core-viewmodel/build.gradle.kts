import extension.addTestsDependencies
import extension.implementation
import extension.kapt

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOL_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK)

        versionCode(BuildAndroidConfig.VERSION_CODE)
        versionName(BuildAndroidConfig.VERSION_NAME)
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.Androidx.CORE_KTX)
    implementation(Dependencies.Androidx.APPCOMPAT)
    implementation(Dependencies.Dagger.DAGGER)
    implementation(Dependencies.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Dagger.DAGGER_COMPILER)

    addTestsDependencies()
}

