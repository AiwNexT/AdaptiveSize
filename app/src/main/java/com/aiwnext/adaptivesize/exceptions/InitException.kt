package com.aiwnext.adaptivesize.exceptions

/**
 * Created by Alexander Kucherenko (AiwNexT) on 17.01.2022
 */

class InitException: Exception() {
    override val message: String = "AdaptiveSize wasn't initialized properly"
}