package com.bayut.test.view.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayut.test.R
import com.bayut.test.databinding.ActivityProductDetailsBinding
import com.bayut.test.model.entity.response.Product
import com.bayut.test.model.util.Constants
import com.bayut.test.model.util.DateUtil
import com.bayut.test.view.BaseActivity
import com.bayut.test.view.adapters.ImagesAdapter
import com.bayut.test.view.extensions.setTransparentStatusWithWhiteIcons

class ProductDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityProductDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)

        setTransparentStatusWithWhiteIcons()

        setToolBar(binding.toolbar)
        intent?.let {
            val product = intent.getSerializableExtra(Constants.PRODUCT_ITEM) as Product?
            product?.let {
                product.image_urls?.let {
                    binding.thumbnailsRv.layoutManager =
                        LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                    binding.thumbnailsRv.adapter = ImagesAdapter(it)
                }

                binding.priceTv.text = product.price

                binding.itemNameTv.text = product.name.orEmpty()

                binding.dateTv.text = product.created_at?.let {
                    DateUtil.formatSelectedDate(
                        DateUtil.dashLongDateTimeFormatWithMs, DateUtil.dayMonthDateFormat,
                        it
                    )
                }.orEmpty()
            }
        }
    }
}