package com.bayut.test.view.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean?) {
    value?.let { view.visibility = if (value) View.VISIBLE else View.GONE }
}