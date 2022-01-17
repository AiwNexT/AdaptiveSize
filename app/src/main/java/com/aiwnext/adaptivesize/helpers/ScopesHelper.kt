package com.aiwnext.adaptivesize.helpers

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import com.aiwnext.adaptivesize.exceptions.MissingScopeException
import com.aiwnext.adaptivesize.exceptions.ScopeExistsException
import com.aiwnext.adaptivesize.models.Scope
import kotlin.jvm.Throws

/**
 * Created by Alexander Kucherenko (AiwNexT) on 17.01.2022
 */

internal class ScopesHelper(
    private val actualSize: Size
) {

    private val scopes by lazy {
        mutableListOf<Scope>()
    }

    @Throws(ScopeExistsException::class)
    fun addScope(
        scopeName: String,
        artBoardSize: IntSize
    ) {
        val existing = scopes.firstOrNull { it.name == scopeName}
        if (existing == null) {
            scopes.add(Scope(scopeName, artBoardSize))
        } else {
            throw ScopeExistsException(scopeName)
        }
    }

    fun removeScope(
        scopeName: String
    ) {
        scopes.removeAll { it.name == scopeName }
    }

    @Throws(MissingScopeException::class)
    private fun getScope(scopeName: String): Scope {
        return scopes.firstOrNull { it.name == scopeName } ?:
            throw MissingScopeException(scopeName)
    }

    @Throws(MissingScopeException::class)
    fun getWidthRatio(scopeName: String): Float {
        return getScope(scopeName).let {
            actualSize.width / it.artBoardSize.width
        }
    }

    @Throws(MissingScopeException::class)
    fun getHeightRatio(scopeName: String): Float {
        return getScope(scopeName).let {
            actualSize.height / it.artBoardSize.height
        }
    }

    @Throws(MissingScopeException::class)
    fun getAverageRatio(scopeName: String): Float {
        return getScope(scopeName).let {
            (getHeightRatio(it.name) + getWidthRatio(it.name)) / 2
        }
    }
}