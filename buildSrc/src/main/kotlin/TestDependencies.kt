object TestDependencies {
    private object Versions {
        const val JUNIT5 = "5.4.0"
        const val MOCKITO = "2.25.0"
        const val EXT_JUNIT = "1.1.2"
        const val ESPRESSO_CORE = "3.3.0"
        const val COROUTINE = "1.4.3"
        const val KOTLIN_TEST = "1.4.31"
    }

    object Coroutine {
        const val COROUTINE_TEST =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINE}"
    }

    object Junit {
        const val EXT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
        const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.KOTLIN_TEST}"
    }

    object Espresso {
        const val CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

    object Jupiter {
        const val JUNIT5_API = "org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT5}"
        const val JUNIT5_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT5}"
        const val JUNIT5_MOCKITO = "org.mockito:mockito-junit-jupiter:${Versions.MOCKITO}"
    }
}
