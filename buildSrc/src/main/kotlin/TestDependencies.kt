object TestDependencies {
    private object Versions {
        const val JUNIT = "4.13.2"
        const val JUNIT5 = "5.4.0"
        const val MOCKITO = "2.20.0"
        const val EXT_JUNIT = "1.1.2"
        const val ESPRESSO_CORE = "3.3.0"
    }

    object Junit {
        const val EXT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
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
