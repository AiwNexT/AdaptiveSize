package com.aiwnext.adaptivesize.exceptions

/**
 * Created by Alexander Kucherenko (AiwNexT) on 17.01.2022
 */

class ScopeExistsException(scopeName: String) : Exception() {
    override val message: String = "Scope with name \"$scopeName\" already registered"
}