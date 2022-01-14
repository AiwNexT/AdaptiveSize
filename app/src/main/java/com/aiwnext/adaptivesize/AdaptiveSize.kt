package com.aiwnext.adaptivesize

import com.aiwnext.adaptivesize.sizes.FloatSize

object AdaptiveSize {

	private var artBoardSize: Pair<Int, Int>? = null
	private var actualSize: Pair<Int, Int>? = null

	fun setup(
		artBoardHeight: Int,
		artBoardWidth: Int,
		actualHeight: Int,
		actualWidth: Int
	) {
		artBoardSize = Pair(artBoardHeight, artBoardWidth)
		actualSize = Pair(actualHeight, actualWidth)
	}

	internal fun adaptiveWidth(width: Float): Float {
		return try {
			val ratio = actualSize!!.second.toFloat() / artBoardSize!!.second.toFloat()
			return width / ratio
		} catch (e: Exception) { width }
	}

	internal fun adaptiveHeight(height: Float): Float {
		return try {
			val ratio = actualSize!!.first.toFloat() / artBoardSize!!.first.toFloat()
			return height / ratio
		} catch (e: Exception) { height }
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