package com.bayut.test.network.networkCall

import androidx.lifecycle.MutableLiveData
import com.bayut.test.entity.response.BaseResponse
import com.bayut.test.util.Constants
import com.bayut.test.network.retrofit.RetrofitObject
import com.bayut.test.util.Enums
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.lang.reflect.Type
import java.net.ConnectException

object NetworkCall {

    fun <T : BaseResponse> makeCall(
        errorTypeConverter: Type,
        requestFun: suspend () -> Response<T>
    ): MutableLiveData<ServerCallBack<T>> {
        val result = MutableLiveData<ServerCallBack<T>>()
        //this is for showing loading on the screen
        result.value = ServerCallBack.loading(null)
        //this is for making the call inside CoroutineScope
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = requestFun()
                withContext(Dispatchers.Main) {
                    response.body()?.headers = response.headers()
                    when {
                        response.code() == Enums.NetworkResponseCodes.SUCCESS.code -> {
                            response.body()?.let { body ->
                                result.value = ServerCallBack.success(body)
                                return@withContext
                            }
                        }
                        response.code() == Enums.NetworkResponseCodes.UnAuthorizedUser.code -> {
                            result.value = ServerCallBack.error(Constants.UNAUTHORIZED_ERROR)

                        }
                        response.errorBody() != null -> {
                            val converter: Converter<ResponseBody, T> =
                                RetrofitObject.getInstance()!!.responseBodyConverter(
                                    errorTypeConverter, arrayOfNulls<Annotation>(0)
                                )
                            try {
                                val errorsResponse = converter.convert(response.errorBody()!!)

                                errorsResponse?.Error?.Message?.let {
                                    result.value = ServerCallBack.error(it, errorsResponse)
                                    return@withContext
                                }
                                errorsResponse?.Error?.Details?.let {
                                    result.value = ServerCallBack.error(it, errorsResponse)
                                    return@withContext
                                }
                                result.value = ServerCallBack.error(Constants.GENERAL_ERROR)
                            } catch (e: Exception) {
                                //this means there is a problem to parse the error body itself
                                result.value = ServerCallBack.error(Constants.GENERAL_ERROR)
                            }
                        }
                        else -> result.value = ServerCallBack.error(Constants.GENERAL_ERROR)
                    }
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    if (e is ConnectException) {
                        //this is no internet exception
                        result.value = ServerCallBack.error(Constants.NO_INTERNET)
                    } else
                        result.value = ServerCallBack.error(Constants.GENERAL_ERROR)
                }
            }
        }
        return result
    }
}