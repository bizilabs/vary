package org.bizilabs.vary.models

import androidx.compose.runtime.Composable

class VaryScope<T> {
    internal val content = mutableMapOf<VarySize, @Composable () -> T>()

    fun xs(content: @Composable () -> T) {
        this.content[VarySize.XS] = content
    }

    fun sm(content: @Composable () -> T) {
        this.content[VarySize.SM] = content
    }

    fun md(content: @Composable () -> T) {
        this.content[VarySize.MD] = content
    }

    fun lg(content: @Composable () -> T) {
        this.content[VarySize.LG] = content
    }

    fun xl(content: @Composable () -> T) {
        this.content[VarySize.XL] = content
    }

    fun xxl(content: @Composable () -> T) {
        this.content[VarySize.XXL] = content
    }
}
