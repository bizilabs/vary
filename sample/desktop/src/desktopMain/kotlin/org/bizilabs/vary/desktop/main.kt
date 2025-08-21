@file:Suppress("ktlint:standard:filename")

package org.bizilabs.vary.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Vary",
        ) {
            window.minimumSize = Dimension(100, 100)
            App()
        }
    }
