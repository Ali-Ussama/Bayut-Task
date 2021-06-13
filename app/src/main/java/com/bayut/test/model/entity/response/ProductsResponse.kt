package com.bayut.test.model.entity.response

import okhttp3.Headers
import java.io.Serializable

data class ProductsResponse(
    val results: List<Product>?,
    val pagination: PaginationData?,
    override var headers: Headers,
    override val UnAuthorizedRequest: Boolean,
    override val Success: Boolean,
    override val Sequence: String,
    override val Error: Error,
    override val Message: String
) : BaseResponse

data class Product(
    val uid: String?,
    val created_at: String?,
    val price: String?,
    val name: String?,
    val image_ids: ArrayList<String>?,
    val image_urls: ArrayList<String>?,
    val image_urls_thumbnails: ArrayList<String>?
):Serializable

data class PaginationData(
    val key: Int?
)