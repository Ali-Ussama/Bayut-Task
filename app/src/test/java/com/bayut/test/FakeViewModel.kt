package com.bayut.test

import androidx.paging.Pager
import androidx.paging.PagingConfig

class FakeViewModel {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20),
        initialKey = 0
    ) {
        FakeDataSource()
    }.flow
}