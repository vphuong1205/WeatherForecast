object TestDependencies {
    private object Versions {
        const val JUNIT = "4.13.2"
        const val EXT_JUNIT = "1.1.2"
        const val ESPRESSO_CORE = "3.3.0"
    }

    object Junit {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val EXT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
    }

    object Espresso {
        const val CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }
}
