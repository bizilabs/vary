package org.bizilabs.vary.models

import androidx.compose.runtime.Composable
import org.bizilabs.vary.models.VarySize.Companion.ordinal

class VaryLayoutScope {
    // can be content=arrayOfNulls<@Composable ()->Unit)?>(VarySize.all.size)
    internal val content = mutableMapOf<VarySize, @Composable () -> Unit>()
    fun sm(content: @Composable () -> Unit) {
        this.content[VarySize.SM] = content
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
class ArrayBasedVaryValueScope<T>{
    private val values = arrayOfNulls<Any?>(VarySize.all.size)

    operator fun set(size: VarySize,value:T?){
        // might want to check if the value is already there
        values[size.ordinal()] =value
    }
    // can be internal or private
    operator fun get(size: VarySize):T?{
        return values[size.ordinal()] as T?
    }
    fun getByOrdinal(ordinal:Int):T?{
        return  values.getOrNull(ordinal) as? T
    }

    // same as before

    fun sm(value: T) {
        this.values[VarySize.SM.ordinal()] = value
    }

    fun md(value: T) {
        this.values[VarySize.MD.ordinal()] = value
    }

    fun lg(value: T) {
        this.values[VarySize.LG.ordinal()] = value
    }

    fun xl(value: T) {
        this.values[VarySize.XL.ordinal()] = value
    }

    fun xxl(value: T) {
        this.values[VarySize.XXL.ordinal()] = value
    }
}
class VaryValueScope<T> {
    // vary size is a sealed class, which is used as a key, it can cause an issue with hashing
    // because assuming you do this.values[VarySize.SM] it will call VarySize.SM.hashcode()
    // then find the hashcode in the map (since we are using VarySize as a key),
    // handle potential hash collissions, then return the value/store the value
    // assuming you use a primitive as a key e.g. VarySize.SM.ordinal() =1, the hash bucket will be
    // array_base_address + (1 * element_size)
    internal val values = mutableMapOf<VarySize, T>()
    fun sm(value: T) {
        this.values[VarySize.SM] = value
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

