package com.aiwnext.adaptivesize

import android.content.Context
import com.aiwnext.adaptivesize.sizes.FloatSize

object AdaptiveSize {

	private var artBoardSize: Pair<Int, Int>? = null
	private var actualSize: Pair<Int, Int>? = null

	fun setup(
		context: Context,
		artBoardHeight: Int,
		artBoardWidth: Int
	) {
		artBoardSize = Pair(artBoardHeight, artBoardWidth)
		val (height, width) = context.resources
			.displayMetrics
			.run { heightPixels / density to widthPixels / density }
		actualSize = Pair(height.toInt(), width.toInt())
	}

	private fun getWidthRatio(): Float {
		return actualSize!!.second.toFloat() / artBoardSize!!.second.toFloat()
	}

	private fun getHeightRatio(): Float {
		return actualSize!!.first.toFloat() / artBoardSize!!.first.toFloat()
	}

	internal fun adaptiveWidth(width: Float): Float {
		return try {
			return width * getWidthRatio()
		} catch (e: Exception) { width }
	}

	internal fun adaptiveHeight(height: Float): Float {
		return try {
			return height * getHeightRatio()
		} catch (e: Exception) { height }
	}

	internal fun adaptiveAverage(value: Float): Float {
		return try {
			return value * ((getHeightRatio() + getWidthRatio()) / 2)
		} catch (e: Exception) { value }
	}

	internal fun keepingAspect(
		height: Float,
		width: Float,
		byHeight: Boolean = true
	): FloatSize {
		return if (byHeight) {
			FloatSize(
				adaptiveHeight(height),
				adaptiveHeight(width)
			)
		} else {
			FloatSize(
				adaptiveWidth(height),
				adaptiveWidth(width)
			)
		}
	}
}