package com.bayut.test.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bayut.test.model.dataSource.ProductsDataSource

class MainViewModel(application: Application) : BaseViewModel(application) {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20),
        initialKey = 0
    ) {
        ProductsDataSource()
    }.flow
        .cachedIn(viewModelScope)
}