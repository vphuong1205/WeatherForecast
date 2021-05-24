package com.nab.phuong.lib_utils

fun String?.searchable(minLength: Int): Boolean =
    !(this.isNullOrEmpty() || this.trim().length < minLength)
