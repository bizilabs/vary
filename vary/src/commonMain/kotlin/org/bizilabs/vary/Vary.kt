package org.bizilabs.vary

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.bizilabs.vary.models.Breakpoints
import org.bizilabs.vary.models.LocalVarySizeData
import org.bizilabs.vary.models.LocalVaryWidth
import org.bizilabs.vary.models.VaryLayoutScope
import org.bizilabs.vary.models.VarySize
import org.bizilabs.vary.models.VaryValueScope
import org.bizilabs.vary.models.breakpoints
import org.bizilabs.vary.models.calculateVaryWidth
import org.bizilabs.vary.models.mapBreakpointToWidth
import org.bizilabs.vary.models.rememberVarySize

@Composable
fun Vary(
    width: Int? = null,
    breakpoints: Breakpoints = breakpoints(),
    content: @Composable () -> Unit,
) {
    val sizes = remember(width, breakpoints) { mapBreakpointToWidth(breakpoints) }
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val actualWidth = calculateVaryWidth(maxWidth, width)
        CompositionLocalProvider(LocalVaryWidth provides actualWidth, LocalVarySizeData provides sizes) {
            content()
        }
    }
}

@Suppress("ComposableNaming")
@Composable
fun vary(
    builder: @Composable VaryLayoutScope.() -> Unit = {},
    xs: @Composable () -> Unit,
) {
    // get the size to width list
    val data = LocalVarySizeData.current
    val width = LocalVaryWidth.current
    val size = rememberVarySize(width, data)

    val scope =
        remember { VaryLayoutScope() }.apply {
            content[VarySize.XS] = xs
            builder()
        }

    val composableToRender =
        remember(size, scope.content) {
            val bestMatch =
                (VarySize.entries.indexOf(size) downTo 0)
                    .asSequence()
                    .mapNotNull { index -> scope.content[VarySize.entries[index]] }
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
    val width = LocalVaryWidth.current
    val data = LocalVarySizeData.current
    val size = rememberVarySize(width, data)

    val scope = remember { VaryValueScope<T>() }.apply(builder)

    return remember(size, scope.values) {
        scope.values[VarySize.XS] = xs
        (VarySize.entries.indexOf(size) downTo 0)
            .asSequence()
            .mapNotNull { index -> scope.values[VarySize.entries[index]] }
            .firstOrNull() ?: xs
    }.also {
        println("Vary > value = $it")
    }
}
