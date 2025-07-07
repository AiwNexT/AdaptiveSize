package com.aiwnext.adaptivesize

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import com.aiwnext.adaptivesize.exceptions.InitException
import com.aiwnext.adaptivesize.helpers.ScopesHelper
import com.aiwnext.adaptivesize.models.AspectSource

class AdaptiveSize {

	companion object {

		private var self: AdaptiveSize? = null

		fun instance(): AdaptiveSize {
			return self ?: AdaptiveSize()
		}
	}

	private val scopesHelper by lazy {
		ScopesHelper(actualSize ?: throw InitException())
	}

	private var actualSize: Size? = null

	init {
	    self = this
	}

	fun setup(
		screenSize: Size,
		artBoardSize: IntSize
	) {
		actualSize = Size(
			height = screenSize.height,
			width = screenSize.width
		)

		val abSize = artBoardSize
		scopesHelper.addScope(Extras.DEFAULT_SCOPE_NAME, abSize)
	}

	fun registerScope(
		scopeName: String,
		artBoardSize: IntSize
	) {
		scopesHelper.addScope(scopeName, artBoardSize)
	}

	fun unregisterScope(scopeName: String) {
		scopesHelper.removeScope(scopeName)
	}

	internal fun adaptiveWidth(
		width: Float,
		scopeName: String? = null
	): Float {
		return try {
			width * scopesHelper.getWidthRatio(scopeName.orDefaultScope())
		} catch (ex: Exception) { width }
	}

	internal fun adaptiveHeight(
		height: Float,
		scopeName: String? = null
	): Float {
		return try {
			height * scopesHelper.getHeightRatio(scopeName.orDefaultScope())
		} catch (ex: Exception) { height }
	}

	internal fun adaptiveAverage(
		value: Float,
		scopeName: String? = null
	): Float {
		return try {
			value * scopesHelper.getAverageRatio(scopeName.orDefaultScope())
		} catch (ex: Exception) { value }
	}

	internal fun keepingAspect(
		height: Float,
		width: Float,
		aspectSource: AspectSource,
		scopeName: String? = null
	): Size {
		return try {
			when (aspectSource) {
				AspectSource.WIDTH -> Size(
					height = adaptiveWidth(height, scopeName),
					width = adaptiveWidth(width, scopeName)
				)
				AspectSource.HEIGHT -> Size(
					height = adaptiveHeight(height, scopeName),
					width = adaptiveHeight(width, scopeName)
				)
				AspectSource.AVERAGE -> Size(
					height = adaptiveAverage(height, scopeName),
					width = adaptiveAverage(width, scopeName)
				)
			}
		} catch (ex: Exception) {
			Size(width, height)
		}
	}

	private fun String?.orDefaultScope(): String {
		return this ?: Extras.DEFAULT_SCOPE_NAME
	}
}