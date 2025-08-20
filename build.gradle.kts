import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    // android
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    // kotlin
    alias(libs.plugins.kotlin.multiplatform) apply false
    // compose
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.hotreload) apply false
    alias(libs.plugins.compose.compiler) apply false
    // others
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlin.android) apply false
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        enableExperimentalRules.set(true)
        additionalEditorconfig.set(
            mapOf(
                "ktlint_standard_package-naming" to "disabled",
            ),
        )
        reporters {
            reporter(ReporterType.JSON)
        }
        filter {
            exclude {
                projectDir
                    .toURI()
                    .relativize(it.file.toURI())
                    .path
                    .contains("/generated/")
            }
            exclude {
                projectDir
                    .toURI()
                    .relativize(it.file.toURI())
                    .path
                    .contains("/build/")
            }
        }
    }
}
