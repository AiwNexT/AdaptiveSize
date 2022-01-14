package com.aiwnext.adaptivesize

import com.aiwnext.adaptivesize.sizes.FloatSize
import com.aiwnext.adaptivesize.sizes.IntSize

fun Float.adaptiveWidth(): Float {
	return AdaptiveSize.adaptiveWidth(this)
}

fun Float.adaptiveHeight(): Float {
	return AdaptiveSize.adaptiveHeight(this)
}

fun Int.adaptiveWidth(): Int {
	return AdaptiveSize.adaptiveWidth(this.toFloat()).toInt()
}

fun Int.adaptiveHeight(): Int {
	return AdaptiveSize.adaptiveHeight(this.toFloat()).toInt()
}

fun Pair<Float, Float>.keepingAspect(byHeight: Boolean = true): FloatSize {
	return AdaptiveSize
		.keepingAspect(this.first, this.second, byHeight)
}

fun Pair<Int, Int>.keepingAspect(byHeight: Boolean = true): IntSize {
	return AdaptiveSize
		.keepingAspect(this.first.toFloat(), this.second.toFloat(), byHeight)
		.let { IntSize(it.height.toInt(), it.width.toInt()) }
}