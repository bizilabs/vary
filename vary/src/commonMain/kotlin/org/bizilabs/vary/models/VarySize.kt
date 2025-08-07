package org.bizilabs.vary.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

sealed class VarySize(val minWidth: Int) {
    data object XS : VarySize(0)
    data object SM : VarySize(640)
    data object MD : VarySize(768)
    data object LG : VarySize(1024)
    data object XL : VarySize(1280)
    data object XXL : VarySize(1536)

    companion object {
        val all = listOf(XS, SM, MD, LG, XL, XXL)
    }
}

val LocalVarySize = compositionLocalOf { 0 }

@Composable
fun rememberVarySize(width: Int): VarySize {
    return remember(width) {
        VarySize.all.last { width >= it.minWidth }
    }
}
