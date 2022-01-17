package com.aiwnext.adaptivesize.extensions

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import com.aiwnext.adaptivesize.AdaptiveSize
import com.aiwnext.adaptivesize.models.AspectSource

fun Float.adaptiveWidth(): Float {
	return AdaptiveSize.instance().adaptiveWidth(this)
}

fun Float.adaptiveHeight(): Float {
	return AdaptiveSize.instance().adaptiveHeight(this)
}

fun Float.adaptiveAverage(): Float {
	return AdaptiveSize.instance().adaptiveAverage(this)
}

fun Int.adaptiveWidth(): Int {
	return AdaptiveSize.instance().adaptiveWidth(this.toFloat()).toInt()
}

fun Int.adaptiveHeight(): Int {
	return AdaptiveSize.instance().adaptiveHeight(this.toFloat()).toInt()
}

fun Int.adaptiveAverage(): Int {
	return AdaptiveSize.instance().adaptiveAverage(this.toFloat()).toInt()
}

fun Size.keepingAspect(aspectSource: AspectSource = AspectSource.HEIGHT): Size {
	return AdaptiveSize.instance().keepingAspect(
		this.height,
		this.width,
		aspectSource
	)
}

fun IntSize.keepingAspect(aspectSource: AspectSource = AspectSource.HEIGHT): IntSize {
	return AdaptiveSize.instance().keepingAspect(
		this.height.toFloat(),
		this.width.toFloat(),
		aspectSource
	).let { IntSize(it.height.toInt(), it.width.toInt()) }
}