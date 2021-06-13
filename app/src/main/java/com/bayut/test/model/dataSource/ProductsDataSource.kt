package com.bayut.test.model.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bayut.test.model.entity.response.Product
import com.bayut.test.model.repository.ProductRepo
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpRetryException

class ProductsDataSource :
    PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            // Start refresh at page 1 if undefined.
            var nextPageNumber: Int? = null
            params.key?.let { pageNumber ->
                if (pageNumber < 0)
                    nextPageNumber = pageNumber.plus(1)
            }
            val response = ProductRepo.getProducts(nextPageNumber)
            var productList = ArrayList<Product>()
            if (response.isSuccessful) {
                response.body()?.let {
                    if (!it.results.isNullOrEmpty()) {
                        productList = it.results as ArrayList<Product>
                    }
                }
                LoadResult.Page(
                    data = productList,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber
                )
            } else {
                //TODO Not best Practice
                LoadResult.Error(HttpRetryException("Retry", 500))
            }
        } catch (ioException: IOException) {
            LoadResult.Error(throwable = ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(throwable = httpException)
        } catch (exception: Exception) {
            LoadResult.Error(throwable = exception)
        }
    }

}