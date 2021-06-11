package com.bayut.test.view

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

open class BaseActivity : AppCompatActivity() {

    private lateinit var loading: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun setToolBar(toolbar: androidx.appcompat.widget.Toolbar, showTitle: Boolean = true) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(showTitle)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun showDefaultLoading(view: View? = null, isBlueBackGround: Boolean = false) {
        if (!this::loading.isInitialized) {
            loading = FrameLayout(this)
            loading.tag = "LOADING"
            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.gravity = Gravity.CENTER
            layoutParams.width = 500
            layoutParams.height = 500
            loading.layoutParams = layoutParams
            loading.isClickable = true
            loading.setBackgroundColor(Color.TRANSPARENT)
            if (view == null) {
                if (this.window.decorView.rootView is ViewGroup)
                    (this.window.decorView.rootView as ViewGroup).addView(loading)
            } else
                if (view is ViewGroup)
                    view.addView(loading)
            val animationView = LottieAnimationView(this)
            animationView.speed = 1.5f
            animationView.repeatMode = LottieDrawable.RESTART
            animationView.repeatCount = LottieDrawable.INFINITE

            animationView.setAnimation("loader.json")

            animationView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            animationView.playAnimation()
            loading.addView(animationView)
            loading.visibility = View.VISIBLE
        }
        loading.visibility = View.VISIBLE
    }

    fun hideDefaultLoading() {
        if (this::loading.isInitialized) {
            loading.visibility = View.GONE
        }
    }

}