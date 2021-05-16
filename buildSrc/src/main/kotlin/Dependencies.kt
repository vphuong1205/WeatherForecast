object Dependencies {
    object Versions {
        const val GRADLE = "4.1.3"
        const val KOTLIN = "1.4.31"
        const val CORE_KTX = "1.3.2"
        const val LIFE_CYCLE_KTX = "2.3.1"
        const val APPCOMPAT = "1.2.0"
        const val CONSTRAINT_LAYOUT = "2.0.4"
        const val MATERIAL = "1.3.0"
        const val RETROFIT = "2.9.0"
        const val DAGGER2 = "2.29.1"
    }

    object Gradle {
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    }

    object Kotlin {
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
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

    object Dagger {
        const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER2}"
        const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER2}"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER2}"
        const val DAGGER_ANDROID_PROCESSOR =
            "com.google.dagger:dagger-android-processor:${Versions.DAGGER2}"
    }
}
