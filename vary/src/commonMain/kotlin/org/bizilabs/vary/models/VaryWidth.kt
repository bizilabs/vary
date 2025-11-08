package org.bizilabs.vary.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import kotlin.math.ceil

val LocalVaryWidth = compositionLocalOf { 0 }

@Composable
fun calculateVaryWidth(
    boxWidthInDp: Dp,
    customWidth: Int?,
): Int {
    // TODO : think of a better way of calculating the width based on the size
    val fullWidth = ceil(boxWidthInDp.value).toInt()
    val actualWidth = customWidth ?: fullWidth
    return actualWidth
}
