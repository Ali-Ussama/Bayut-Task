package com.bayut.test.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.bayut.test.R
import com.bayut.test.databinding.ActivityMainBinding
import com.bayut.test.model.entity.response.Product
import com.bayut.test.model.network.networkCall.ServerCallBack
import com.bayut.test.view.BaseActivity
import com.bayut.test.view.adapters.ProductClickListener
import com.bayut.test.view.adapters.ProductsAdapter
import com.bayut.test.view.adapters.ProductsLoadStateAdapter
import com.bayut.test.viewModel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivity : BaseActivity(), ProductClickListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val productsAdapter = ProductsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            displayProducts()
        }

        displayProducts()
    }

    private fun displayProducts() {
        binding.productsRv.adapter = productsAdapter.withLoadStateHeaderAndFooter(
            header = ProductsLoadStateAdapter(productsAdapter::retry),
            footer = ProductsLoadStateAdapter(productsAdapter::retry)
        )

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                productsAdapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            productsAdapter.loadStateFlow.collectLatest {loadState ->
                if (loadState.refresh is LoadState.Loading){
                    showDefaultLoading()
                }else{
                    hideDefaultLoading()
                }
            }
        }
    }

    override fun onProductClicked(product: Product?) {

    }
}