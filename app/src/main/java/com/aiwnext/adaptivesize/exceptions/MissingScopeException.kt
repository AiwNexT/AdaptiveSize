package com.aiwnext.adaptivesize.exceptions

/**
 * Created by Alexander Kucherenko (AiwNexT) on 17.01.2022
 */

class MissingScopeException(scopeName: String): Exception() {
    override val message: String = "Unable to find scope with name \"$scopeName\""
}