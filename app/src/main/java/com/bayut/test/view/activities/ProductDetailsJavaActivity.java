package com.bayut.test.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bayut.test.R;
import com.bayut.test.databinding.ActivityProductDetailsBinding;
import com.bayut.test.model.entity.response.Product;
import com.bayut.test.model.util.Constants;
import com.bayut.test.model.util.DateUtil;
import com.bayut.test.view.BaseActivity;
import com.bayut.test.view.adapters.ImagesAdapter;

import org.jetbrains.annotations.Nullable;

public class ProductDetailsJavaActivity extends BaseActivity {

    ActivityProductDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);

        setTransparentStatusWithIconsWhite();

        setToolbar();

        setDataOnView();
    }

    private void setDataOnView() {
        Intent intent = getIntent();
        if (intent != null) {
            Product product = (Product) intent.getSerializableExtra(Constants.PRODUCT_ITEM);
            if (product != null) {
                if (product.getImage_urls_thumbnails() != null && !product.getImage_urls_thumbnails().isEmpty()) {
                    binding.thumbnailsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    binding.thumbnailsRv.setAdapter(new ImagesAdapter(product.getImage_urls_thumbnails(), null, null, null));
                }

                if (product.getPrice() != null) {
                    binding.priceTv.setText(product.getPrice());
                }

                if (product.getName() != null) {
                    binding.itemNameTv.setText(product.getName());
                }

                if (product.getCreated_at() != null) {
                    String dateString = DateUtil.formatSelectedDate(DateUtil.dashLongDateTimeFormatWithMs, DateUtil.dayMonthDateFormat, product.getCreated_at());
                    binding.dateTv.setText(dateString);
                }
            }


        }
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void setTransparentStatusWithIconsWhite() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
