package com.aiwnext.adaptivesize

import android.content.Context
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import com.aiwnext.adaptivesize.helpers.ScopesHelper
import com.aiwnext.adaptivesize.models.AspectSource

internal class AdaptiveSize {

	companion object {

		private var self: AdaptiveSize? = null

		fun instance(): AdaptiveSize {
			return self ?: AdaptiveSize()
		}
	}

	private val scopesHelper by lazy {
		ScopesHelper(actualSize)
	}

	private var actualSize: Size? = null

	init {
	    self = this
	}

	fun setup(
		context: Context,
		artBoardSize: IntSize? = null
	) {
		artBoardSize
		val (height, width) = context.resources
			.displayMetrics
			.run { heightPixels / density to widthPixels / density }
		actualSize = Size(
			height = height,
			width = width
		)
	}

	private fun getWidthRatio(): Float {
		return actualSize!!.width / artBoardSize!!.width
	}

	private fun getHeightRatio(): Float {
		return actualSize!!.height / artBoardSize!!.height
	}

	private fun getAverageRatio(): Float {
		return ((getHeightRatio() + getWidthRatio()) / 2)
	}

	fun adaptiveWidth(width: Float): Float {
		return try {
			return width * getWidthRatio()
		} catch (e: Exception) { width }
	}

	fun adaptiveHeight(height: Float): Float {
		return try {
			return height * getHeightRatio()
		} catch (e: Exception) { height }
	}

	fun adaptiveAverage(value: Float): Float {
		return try {
			return value * getAverageRatio()
		} catch (e: Exception) { value }
	}

	 fun keepingAspect(
		height: Float,
		width: Float,
		aspectSource: AspectSource
	): Size {
		return when (aspectSource) {
			AspectSource.WIDTH -> Size(
				height = adaptiveWidth(height),
				width = adaptiveWidth(width)
			)
			AspectSource.HEIGHT -> Size(
				height = adaptiveHeight(height),
				width = adaptiveHeight(width)
			)
			AspectSource.AVERAGE -> Size(
				height = adaptiveAverage(height),
				width = adaptiveAverage(width)
			)
		}
	}
}