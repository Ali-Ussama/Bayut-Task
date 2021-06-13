package com.bayut.test.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bayut.test.databinding.ProductRowItemBinding
import com.bayut.test.model.entity.response.Product
import com.bayut.test.model.util.DateUtil
import com.bayut.test.view.adapters.viewHolders.ProductAdapterViewHolder

class ProductsAdapter(private val productListener: ProductClickListener?) :
    PagingDataAdapter<Product, ProductAdapterViewHolder>(PRODUCTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        return ProductAdapterViewHolder(
            ProductRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            productListener
        )
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.uid == newItem.uid

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem
        }
    }
}


interface ProductClickListener {
    fun onProductClicked(product: Product?,binding: ProductRowItemBinding?)
}