object Dependencies {
    object Versions {
        const val GRADLE = "4.1.3"
        const val KOTLIN = "1.4.31"
        const val KOTLIN_COROUTINES_CORE = "1.5.0"
        const val CORE_KTX = "1.3.2"
        const val LIFE_CYCLE_KTX = "2.3.1"
        const val APPCOMPAT = "1.2.0"
        const val CONSTRAINT_LAYOUT = "2.0.4"
        const val MATERIAL = "1.3.0"
        const val RETROFIT = "2.9.0"
        const val DAGGER2 = "2.29.1"
        const val NAVIGATION = "2.3.5"
        const val ROOM = "2.3.0"
        const val MOSHI = "1.12.0"
        const val ROOT_BEER = "0.0.9"
    }

    object Gradle {
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    }

    object Kotlin {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
        const val KOTLIN_COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES_CORE}"
    }

    object Androidx {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val LIVE_DATA_KTX =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFE_CYCLE_KTX}"
        const val VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE_KTX}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    }

    object Retrofit {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    }

    object Moshi {
        const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
        const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    }

    object Dagger {
        const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER2}"
        const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER2}"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER2}"
        const val DAGGER_ANDROID_PROCESSOR =
            "com.google.dagger:dagger-android-processor:${Versions.DAGGER2}"
    }

    object Navigation {
        const val NAVIGATION_FRAGMENT_KTX =
            "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_KTX =
            "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    }

    object Room {
        const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

        // optional - Kotlin Extensions and Coroutines support for Room
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    }

    object RootBeer {
        const val ROOT_BEER = "com.scottyab:rootbeer-lib:${Versions.ROOT_BEER}"
    }
}
