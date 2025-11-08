package org.bizilabs.vary.models

@ConsistentCopyVisibility
data class Breakpoints internal constructor(
    val xs: Int,
    val sm: Int,
    val md: Int,
    val lg: Int,
    val xl: Int,
    val xxl: Int,
) {
    object Default {
        const val XS = 0
        const val SM = 640
        const val MD = 768
        const val LG = 1024
        const val XL = 1280
        const val XXL = 1536
    }

    init {
        checkValidBreakPoints()
    }

    private fun checkValidBreakPoints() {
        if (xs < 0) throw IllegalSizeException("xs", xs)
        if (sm < xs) throw IllegalSizeException("sm", sm)
        if (md < xs) throw IllegalSizeException("md", md)
        if (lg < md) throw IllegalSizeException("lg", lg)
        if (xl < lg) throw IllegalSizeException("xl", xl)
        if (xxl < xl) throw IllegalSizeException("xxl", xxl)
    }
}

fun breakpoints(
    xs: Int = Breakpoints.Default.XS,
    sm: Int = Breakpoints.Default.SM,
    md: Int = Breakpoints.Default.MD,
    lg: Int = Breakpoints.Default.LG,
    xl: Int = Breakpoints.Default.XL,
    xxl: Int = Breakpoints.Default.XXL,
): Breakpoints =
    Breakpoints(
        xs = xs,
        sm = sm,
        md = md,
        lg = lg,
        xl = xl,
        xxl = xxl,
    )
