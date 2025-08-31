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
        // ordinal based look up which is pre computed
        fun VarySize.ordinal(): Int = when(this) {
            XS -> 0
            SM -> 1
            MD -> 2
            LG -> 3
            XL -> 4
            XXL -> 5
        }
    }
}

val LocalVarySize = compositionLocalOf { 0 }

@Composable
fun rememberVarySize(width: Int): VarySize {
    return remember(width) {
        // find the last matching size. We eliminate use of 'last' because it has to create a listIterator
        // behind the scenes...this is O(n), we can break it down to O(log n) using binary search
        for (i in VarySize.all.lastIndex downTo 0){
            val size = VarySize.all[i]
            if(width >= size.minWidth) return@remember  size
        }
        VarySize.XS // this is the fallback if the predicate does not work
    }
}

@Composable
fun rememberVarySizeUsingBinary(width: Int): VarySize {
    return remember(width) {
        var left =0
        var right = VarySize.all.lastIndex
        var result: VarySize = VarySize.XS
        while (left <= right){
            val mid = (left+right)/2
            val midSize = VarySize.all[mid]
            if (width >= midSize.minWidth){
                result = midSize
                left = mid+1
            }else{
                right = mid-1
            }
        }
        result
    }
}
