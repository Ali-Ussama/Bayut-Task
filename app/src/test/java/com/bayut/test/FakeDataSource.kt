package com.bayut.test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bayut.test.model.entity.response.Product

class FakeDataSource  :
    PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {

        // Start refresh at page 1 if undefined.
        var nextPageNumber: Int? = null
        params.key?.let { pageNumber ->
            if (pageNumber < 2)
                nextPageNumber = pageNumber.plus(1)
        }

        return LoadResult.Page(
            data = ArrayList<Product>().apply {
                for (i in 0..1) {
                    add(
                        Product(
                            uid = "4878bf592579410fba52941d00b62f94",
                            created_at = "2019-02-24 04:04:17.566515",
                            price = "AED 5",
                            name = "Notebook",
                            image_ids = generateImageIds(),
                            image_urls = generateImageUrls(),
                            image_urls_thumbnails = generateImageThumbnailsUrls()
                        )
                    )
                }
            },
            prevKey = null, // Only paging forward.
            nextKey = nextPageNumber
        )

    }

    private fun generateImageIds(): ArrayList<String> {
        return ArrayList<String>().apply {
            add("9355183956e3445e89735d877b798689")
        }
    }

    private fun generateImageUrls(): ArrayList<String> {
        return ArrayList<String>().apply {
            add(
                "https://demo-app-photos-45687895456123.s3.amazonaws.com/93"
            )
        }
    }

    private fun generateImageThumbnailsUrls(): ArrayList<String> {
        return ArrayList<String>().apply {
            add(
                "https://demo-app-photos-45687895456123.s3.amazonaws.com/94"
            )
        }
    }
}