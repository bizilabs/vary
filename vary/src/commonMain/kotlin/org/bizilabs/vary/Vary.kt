package io.github.kotlin.fibonacci.org.bizilabs.vary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalWindowInfo
import io.github.kotlin.fibonacci.org.bizilabs.vary.models.VaryLayoutScope
import io.github.kotlin.fibonacci.org.bizilabs.vary.models.VaryValueScope
import org.bizilabs.vary.models.LocalVarySize
import org.bizilabs.vary.models.VarySize
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
    builder: @Composable VaryLayoutScope.() -> Unit,
    sm: @Composable () -> Unit
) {
    val width = LocalVarySize.current
    val windowClass = rememberVarySize(width)

    val scope = remember { VaryLayoutScope() }.apply {
        content[VarySize.SM] = sm
        builder()
    }

    val composableToRender = remember(windowClass, scope.content) {
        val bestMatch = (VarySize.all.indexOf(windowClass) downTo 0)
            .asSequence()
            .mapNotNull { index -> scope.content[VarySize.all[index]] }
            .firstOrNull()
        bestMatch ?: sm
    }

    composableToRender()

}

@Composable
fun <T> vary(
    builder: VaryValueScope<T>.() -> Unit,
    sm: T
): T {
    val width = LocalVarySize.current
    val windowClass = rememberVarySize(width)

    val scope = remember { VaryValueScope<T>() }.apply(builder)

    return remember(windowClass, scope.values) {
        scope.values[VarySize.SM] = sm
        (VarySize.all.indexOf(windowClass) downTo 0)
            .asSequence()
            .mapNotNull { index -> scope.values[VarySize.all[index]] }
            .firstOrNull() ?: sm
    }
}
