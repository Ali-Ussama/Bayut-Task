package com.bayut.test.model.network.retrofit

class ServiceFactory {

    companion object {

        @Synchronized
        fun <T> create(service: Class<T>): T {
            return RetrofitObject.getInstance()?.create(service)!!
        }
    }
}