import extension.addAndroidTestsDependencies
import extension.addTestsDependencies
import extension.implementation

plugins {
    id(BuildPlugins.ANDROID_DYNAMIC_FEATURE)
    id(BuildPlugins.KOTLIN_ANDROID)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK)
    buildToolsVersion(BuildAndroidConfig.BUILD_TOOL_VERSION)

    defaultConfig {
        applicationId("com.nab.phuong.feature_forecast")
        minSdkVersion(BuildAndroidConfig.MIN_SDK)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK)
        versionCode(BuildAndroidConfig.VERSION_CODE)
        versionName(BuildAndroidConfig.VERSION_NAME)
        testInstrumentationRunner(BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER)
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = false
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
    implementation(project(ModuleDependencies.APP))
    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.Androidx.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Androidx.CORE_KTX)
    implementation(Dependencies.Retrofit.RETROFIT)


    api(Dependencies.Navigation.NAVIGATION_UI_KTX)
    api(Dependencies.Navigation.NAVIGATION_FRAGMENT_KTX)
    api(Dependencies.Navigation.NAVIGATION_DYNAMIC_FEATURE_FRAGMENT)

    addTestsDependencies()
    addAndroidTestsDependencies()
}
