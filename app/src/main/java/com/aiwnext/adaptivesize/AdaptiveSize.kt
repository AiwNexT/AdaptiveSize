package com.aiwnext.adaptivesize

import android.content.Context
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
		context: Context,
		artBoardSize: IntSize? = null
	) {
		val (height, width) = context.resources
			.displayMetrics
			.run { heightPixels / density to widthPixels / density }
		actualSize = Size(
			height = height,
			width = width
		)

		val abSize = artBoardSize ?: IntSize(
			Extras.DEFAULT_ARTBOARD_WIDTH,
			Extras.DEFAULT_ARTBOARD_HEIGHT
		)
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
		return width * scopesHelper.getWidthRatio(scopeName.orDefaultScope())
	}

	internal fun adaptiveHeight(
		height: Float,
		scopeName: String? = null
	): Float {
		return height * scopesHelper.getHeightRatio(scopeName.orDefaultScope())
	}

	internal fun adaptiveAverage(
		value: Float,
		scopeName: String? = null
	): Float {
		return value * scopesHelper.getAverageRatio(scopeName.orDefaultScope())
	}

	internal fun keepingAspect(
		height: Float,
		width: Float,
		aspectSource: AspectSource,
		scopeName: String? = null
	): Size {
		return when (aspectSource) {
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
	}

	private fun String?.orDefaultScope(): String {
		return this ?: Extras.DEFAULT_SCOPE_NAME
	}
}