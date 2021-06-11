package com.bayut.test.util

object Enums {

    enum class NetworkResponseCodes(val code: Int) {
        UnAuthorizedUser(401),
        SUCCESS(200),
        ERROR(-1)
    }
}