package com.bayut.test.view.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.bayut.test.view.adapters.viewHolders.ProductsLoadStateViewHolder

class ProductsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ProductsLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = ProductsLoadStateViewHolder(parent, retry)

    override fun onBindViewHolder(
        holder: ProductsLoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}