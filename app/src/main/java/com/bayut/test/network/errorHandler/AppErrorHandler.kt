package com.bayut.test.network.errorHandler

import android.view.View
import androidx.core.text.HtmlCompat


/****
 * this class is responsible for showing the Error
 *
 ****/
object AppErrorHandler {

    fun showError(view: View, message: String?) {
        // find the CoordinatorLayout id
        if (message != null) {
            var messageSpinner =
                HtmlCompat.fromHtml(
                    "<font color=\"#ffffff\">".plus(message).plus("</font>"),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            //show show the error message in either sanckbar or seprated fragment
        }
    }

    fun showError(view: View, message: String?, maxLines: Int) {
        // find the CoordinatorLayout id
        if (message != null && message.isNotEmpty()) {
            var messageSpinner =
                HtmlCompat.fromHtml(
                    "<font color=\"#ffffff\">".plus(message).plus("</font>"),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            //show show the error message in either sanckbar or seprated fragment
        }
    }
}