package org.bizilabs.vary.models

class IllegalSizeException(
    val size: String,
    val value: Int,
) : RuntimeException("Illegal size for $size. The declared($value) is not valid.")
