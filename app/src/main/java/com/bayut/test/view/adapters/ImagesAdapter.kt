package com.bayut.test.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayut.test.databinding.ImageRowItemBinding
import com.bayut.test.databinding.ProductRowItemBinding
import com.bayut.test.model.entity.response.Product
import com.bumptech.glide.Glide

class ImagesAdapter(
    private val images: ArrayList<String>,
    private val product: Product? = null,
    private val listener: ProductClickListener? = null,
    private val productBinding: ProductRowItemBinding? = null
) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ImageRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            product,
            listener,
            productBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(imageUrl = images[position])

    override fun getItemCount(): Int = images.size

    class ViewHolder(private val binding: ImageRowItemBinding,
                     private val product: Product?,
                     private val listener: ProductClickListener?,
                     private val productBinding: ProductRowItemBinding?) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {

            displayImage(imageUrl)

            binding.productIv.setOnClickListener {
                listener?.onProductClicked(product,productBinding)
            }
            binding.refreshIc.setOnClickListener {
                binding.productIv.visibility = View.GONE
                displayImage(imageUrl)
            }
        }

        private fun displayImage(imageUrl: String) {
            if (imageUrl.isNotEmpty()) {
                binding.productIv.visibility = View.VISIBLE
                binding.refreshIc.visibility = View.GONE
                Glide.with(binding.root.context).load(imageUrl).into(binding.productIv)
            } else {
                binding.productIv.visibility = View.GONE
                binding.refreshIc.visibility = View.VISIBLE
            }
        }
    }
}