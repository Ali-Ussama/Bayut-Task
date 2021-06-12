package com.bayut.test.view.adapters.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.bayut.test.R
import com.bayut.test.databinding.LoadStateItemBinding

class ProductsLoadStateViewHolder(
    parent: ViewGroup,
    val retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.load_state_item, parent, false)
) {
    private val binding = LoadStateItemBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        binding.isLoading = loadState is LoadState.Loading
        binding.isRetryAllowed = loadState is LoadState.Error

        if (loadState is LoadState.Error) {
            binding.retryIc.setOnClickListener {
                it.setOnClickListener { retry() }
            }
        }
    }
}