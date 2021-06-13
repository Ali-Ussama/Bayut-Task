package com.bayut.test.model.network.retrofit

import com.bayut.test.model.util.LanguageUtil
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val request = request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Accept-Language", LanguageUtil.getLocal())

        proceed(request.build())
    }
}