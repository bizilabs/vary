package org.bizilabs.vary.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.collections.immutable.ImmutableList

internal enum class VarySize {
    XS,
    SM,
    MD,
    LG,
    XL,
    XXL,
}

@Composable
internal fun rememberVarySize(
    width: Int,
    data: ImmutableList<VarySizeData>,
): VarySize =
    remember(width) {
        println("Remember > width = $width")
        data.last { width >= it.width }.size.also {
            println("Remember > size = $it")
        }
    }
