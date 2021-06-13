package com.bayut.test.view.adapters.viewHolders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bayut.test.databinding.ProductRowItemBinding
import com.bayut.test.model.entity.response.Product
import com.bayut.test.model.util.DateUtil
import com.bayut.test.view.adapters.ImagesAdapter
import com.bayut.test.view.adapters.ProductClickListener

class ProductAdapterViewHolder(
    private val binding: ProductRowItemBinding,
    private val listener: ProductClickListener?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        product.image_urls?.let {
            binding.thumbnailsRv.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.thumbnailsRv.adapter = ImagesAdapter(it,product,listener,binding)
        }

        binding.priceTv.text = product.price

        binding.itemNameTv.text = product.name.orEmpty()

        binding.dateTv.text = product.created_at?.let {
            DateUtil.formatSelectedDate(
                DateUtil.dashLongDateTimeFormatWithMs, DateUtil.dayMonthDateFormat,
                it
            )
        }.orEmpty()

        itemView.setOnClickListener {
            listener?.onProductClicked(product,binding)
        }
    }
}