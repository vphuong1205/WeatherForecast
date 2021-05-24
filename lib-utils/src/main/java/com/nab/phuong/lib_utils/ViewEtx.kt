package com.nab.phuong.lib_utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}
