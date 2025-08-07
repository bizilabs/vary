package io.github.kotlin.fibonacci.org.bizilabs.vary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalWindowInfo
import org.bizilabs.vary.models.LocalVarySize
import org.bizilabs.vary.models.VaryScope
import org.bizilabs.vary.models.VarySize
import org.bizilabs.vary.models.rememberVarySize

@Composable
fun VaryInit(
    width: Int = LocalWindowInfo.current.containerSize.width,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalVarySize provides width) {
        content()
    }
}

@Composable
fun <T> vary(content: @Composable VaryScope<T>.() -> T): T {
    val width = LocalVarySize.current
    val windowClass = rememberVarySize(width)
    val varyScope = remember { VaryScope<T>() }.apply { content() }

    val composableToRender = remember(windowClass, varyScope.content) {
        val bestMatch = (VarySize.all.indexOf(windowClass) downTo 0)
            .asSequence()
            .mapNotNull { index -> varyScope.content[VarySize.all[index]] }
            .firstOrNull()

        bestMatch ?: varyScope.content[VarySize.SM]
    }

    return composableToRender?.invoke() ?: varyScope.content()
}

@Composable
fun <T> vary(default : T, content: @Composable VaryScope<T>.() -> Unit): T {
    val width = LocalVarySize.current
    val windowClass = rememberVarySize(width)
    val varyScope = remember { VaryScope<T>() }.apply { content() }

    val composableToRender = remember(windowClass, varyScope.content) {
        val bestMatch = (VarySize.all.indexOf(windowClass) downTo 0)
            .asSequence()
            .mapNotNull { index -> varyScope.content[VarySize.all[index]] }
            .firstOrNull()

        bestMatch
    }

    return composableToRender?.invoke() ?: default
}
