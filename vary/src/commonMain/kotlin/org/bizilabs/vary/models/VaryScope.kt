package org.bizilabs.vary.models

import androidx.compose.runtime.Composable

class VaryLayoutScope {
    internal val content = mutableMapOf<VarySize, @Composable () -> Unit>()
    fun xs(content: @Composable () -> Unit) {
        this.content[VarySize.XS] = content
    }

    fun md(content: @Composable () -> Unit) {
        this.content[VarySize.MD] = content
    }

    fun lg(content: @Composable () -> Unit) {
        this.content[VarySize.LG] = content
    }

    fun xl(content: @Composable () -> Unit) {
        this.content[VarySize.XL] = content
    }

    fun xxl(content: @Composable () -> Unit) {
        this.content[VarySize.XXL] = content
    }
}

class VaryValueScope<T> {
    internal val values = mutableMapOf<VarySize, T>()
    fun xs(value: T) {
        this.values[VarySize.XS] = value
    }

    fun md(value: T) {
        this.values[VarySize.MD] = value
    }

    fun lg(value: T) {
        this.values[VarySize.LG] = value
    }

    fun xl(value: T) {
        this.values[VarySize.XL] = value
    }

    fun xxl(value: T) {
        this.values[VarySize.XXL] = value
    }
}

