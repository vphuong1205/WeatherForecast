import extension.addAndroidTestsDependencies
import extension.addTestsDependencies
import extension.implementation
import extension.kapt

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOL_VERSION)


    defaultConfig {
        applicationId(BuildAndroidConfig.APPLICATION_ID)
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

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
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
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.Androidx.CORE_KTX)
    implementation(Dependencies.Androidx.LIVE_DATA_KTX)
    implementation(Dependencies.Androidx.VIEWMODEL_KTX)
    implementation(Dependencies.Androidx.APPCOMPAT)
    implementation(Dependencies.Androidx.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Dagger.DAGGER)
    implementation(Dependencies.Dagger.DAGGER_ANDROID)
    implementation(Dependencies.Retrofit.RETROFIT)
    kapt(Dependencies.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Dagger.DAGGER_COMPILER)

    api(Dependencies.Navigation.NAVIGATION_UI_KTX)
    api(Dependencies.Navigation.NAVIGATION_FRAGMENT_KTX)

    implementation(project(ModuleDependencies.CORE_NETWORK))
    implementation(project(ModuleDependencies.CORE_VIEWMODEL))
    implementation(project(ModuleDependencies.FEATURE_FORECAST))

    addTestsDependencies()
    addAndroidTestsDependencies()
}
