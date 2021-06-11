package com.bayut.test.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bayut.test.R
import com.bayut.test.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        showDefaultLoading()
    }
}