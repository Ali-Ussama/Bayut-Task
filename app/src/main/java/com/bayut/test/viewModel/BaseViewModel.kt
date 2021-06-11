package com.bayut.test.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class BaseViewModel(application: Application) : AndroidViewModel(application) {


    fun getBaseContext(): Context {
        return getApplication<Application>().baseContext
    }
}