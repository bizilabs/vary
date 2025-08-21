package org.bizilabs.vary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalWindowInfo
import org.bizilabs.vary.models.LocalVarySize
import org.bizilabs.vary.models.VaryLayoutScope
import org.bizilabs.vary.models.VarySize
import org.bizilabs.vary.models.VaryValueScope
import org.bizilabs.vary.models.rememberVarySize

@Composable
fun Vary(
    width: Int = LocalWindowInfo.current.containerSize.width,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalVarySize provides width) {
        content()
    }
}

@Suppress("ComposableNaming")
@Composable
fun vary(
    builder: @Composable VaryLayoutScope.() -> Unit = {},
    xs: @Composable () -> Unit,
) {
    val width = LocalVarySize.current
    val size = rememberVarySize(width)

    val scope = remember { VaryLayoutScope() }.apply {
        content[VarySize.XS] = xs
        builder()
    }

    val composableToRender = remember(size, scope.content) {
        val bestMatch = (VarySize.all.indexOf(size) downTo 0)
            .asSequence()
            .mapNotNull { index -> scope.content[VarySize.all[index]] }
            .firstOrNull()
        bestMatch ?: xs
    }

    composableToRender()

}

@Composable
fun <T> vary(
    xs: T,
    builder: VaryValueScope<T>.() -> Unit = {},
): T {
    val width = LocalVarySize.current
    val size = rememberVarySize(width)

    val scope = remember { VaryValueScope<T>() }.apply(builder)

    return remember(size, scope.values) {
        scope.values[VarySize.XS] = xs
        (VarySize.all.indexOf(size) downTo 0)
            .asSequence()
            .mapNotNull { index -> scope.values[VarySize.all[index]] }
            .firstOrNull() ?: xs
    }
}
