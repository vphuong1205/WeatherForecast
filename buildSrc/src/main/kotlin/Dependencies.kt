object Dependencies {

    object Gradle {
        private const val VERSION = "4.1.3"
        const val GRADLE = "com.android.tools.build:gradle:${VERSION}"
    }

    object Kotlin {
        private const val version = "1.4.31"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version}"
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${version}"

        private const val cVersion = "1.5.0"
        const val KOTLIN_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$cVersion"
    }

    object Androidx {
        private const val coreKtxVersion = "1.3.2"
        const val CORE_KTX = "androidx.core:core-ktx:${coreKtxVersion}"

        private const val appCompatVersion = "1.2.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${appCompatVersion}"

        private const val clVersion = "2.0.4"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${clVersion}"

        private const val lifecycleVersion = "2.3.1"
        const val LIVE_DATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycleVersion}"
        const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycleVersion}"
    }

    object Google {
        private const val version = "1.3.0"
        const val MATERIAL = "com.google.android.material:material:${version}"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${version}"
        const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${version}"
    }

    object Moshi {
        private const val version = "1.12.0"
        const val MOSHI = "com.squareup.moshi:moshi-kotlin:${version}"
        const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${version}"
    }

    object Dagger {
        private const val version = "2.29.1"

        const val DAGGER = "com.google.dagger:dagger:${version}"
        const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${version}"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${version}"
        const val DAGGER_ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:${version}"
    }

    object DaggerHilt {
        private const val version = "2.35"
        const val HILT_ANDROID = "com.google.dagger:hilt-android:$version"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:$version"
        const val HILT_ANDROID_GRADLE = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Navigation {
        private const val version = "2.3.5"

        const val NAVIGATION_FRAGMENT_KTX =
            "androidx.navigation:navigation-fragment-ktx:${version}"
        const val NAVIGATION_UI_KTX =
            "androidx.navigation:navigation-ui-ktx:${version}"
    }

    object Room {
        private const val version = "2.3.0"

        const val ROOM = "androidx.room:room-runtime:${version}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${version}"

        // optional - Kotlin Extensions and Coroutines support for Room
        const val ROOM_KTX = "androidx.room:room-ktx:${version}"
    }

    object RootBeer {
        private const val version = "0.0.9"
        const val ROOT_BEER = "com.scottyab:rootbeer-lib:${version}"
    }
}
