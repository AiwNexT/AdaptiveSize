package com.aiwnext.adaptivesize.extensions

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import com.aiwnext.adaptivesize.AdaptiveSize
import com.aiwnext.adaptivesize.models.AspectSource

fun Float.adaptiveWidth(scopeName: String? = null): Float {
	return AdaptiveSize.instance().adaptiveWidth(this, scopeName)
}

fun Float.adaptiveHeight(scopeName: String? = null): Float {
	return AdaptiveSize.instance().adaptiveHeight(this, scopeName)
}

fun Float.adaptiveAverage(scopeName: String? = null): Float {
	return AdaptiveSize.instance().adaptiveAverage(this, scopeName)
}

fun Int.adaptiveWidth(scopeName: String? = null): Int {
	return AdaptiveSize.instance().adaptiveWidth(this.toFloat(), scopeName).toInt()
}

fun Int.adaptiveHeight(scopeName: String? = null): Int {
	return AdaptiveSize.instance().adaptiveHeight(this.toFloat(), scopeName).toInt()
}

fun Int.adaptiveAverage(scopeName: String? = null): Int {
	return AdaptiveSize.instance().adaptiveAverage(this.toFloat(), scopeName).toInt()
}

fun Size.keepingAspect(
	aspectSource: AspectSource = AspectSource.HEIGHT,
	scopeName: String? = null
): Size {
	return AdaptiveSize.instance().keepingAspect(
		this.height,
		this.width,
		aspectSource,
		scopeName
	)
}

fun IntSize.keepingAspect(
	aspectSource: AspectSource = AspectSource.HEIGHT,
	scopeName: String? = null
): IntSize {
	return AdaptiveSize.instance().keepingAspect(
		this.height.toFloat(),
		this.width.toFloat(),
		aspectSource,
		scopeName
	).let { IntSize(it.height.toInt(), it.width.toInt()) }
}