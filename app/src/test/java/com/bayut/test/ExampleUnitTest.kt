package com.bayut.test

import androidx.paging.PagingSource
import androidx.paging.filter
import com.bayut.test.model.entity.response.Product
import com.bayut.test.model.util.DateUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.coroutines.coroutineContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private var viewModel: FakeViewModel? = null

    @Test
    fun test_correct_formatting_date() {
        assertEquals(
            "24 Feb",
            DateUtil.formatSelectedDate(
                DateUtil.dashLongDateTimeFormatWithMs,
                DateUtil.dayMonthDateFormat,
                "2019-02-24 04:04:17.566515"
            )
        )
    }

    @Test
    fun test_wrong_formatting_date() {
        assertNotEquals(
            "4 Jan",
            DateUtil.formatSelectedDate(
                DateUtil.dashLongDateTimeFormatWithMs,
                DateUtil.dayMonthDateFormat,
                "2019-02-24 04:04:17.566515"
            )
        )

    }

    @Before
    fun initViewModel() {
        viewModel = FakeViewModel()
    }

    @Test
    fun test_paging_data_source() {
        runBlocking {
            val pagingSource = FakeDataSource()

            assertEquals(
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ),
                PagingSource.LoadResult.Page(
                    data = generateProductsList(),
                    prevKey = null,
                    nextKey = null
                )
            )
        }
    }

    private fun generateProductsList(): List<Product> {
        val products = ArrayList<Product>().apply {
            for (i in 0..10) {
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
        }
        return listOf(
            products[0],
            products[1]
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