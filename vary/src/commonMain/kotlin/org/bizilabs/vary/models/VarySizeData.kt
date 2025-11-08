package org.bizilabs.vary.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

@Stable
internal data class VarySizeData(
    val size: VarySize,
    val width: Int,
)

internal val LocalVarySizeData = compositionLocalOf { emptyList<VarySizeData>() }

internal fun mapBreakpointToWidth(breakpoints: Breakpoints) =
    VarySize.entries.map {
        val breakpoint =
            when (it) {
                VarySize.XS -> breakpoints.xs
                VarySize.SM -> breakpoints.sm
                VarySize.MD -> breakpoints.md
                VarySize.LG -> breakpoints.lg
                VarySize.XL -> breakpoints.xl
                VarySize.XXL -> breakpoints.xxl
            }
        VarySizeData(it, breakpoint)
    }
