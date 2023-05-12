package com.example.androidonetask.presentation.utils

import android.content.res.Resources.getSystem

val Int.px get() = (this * getSystem().displayMetrics.density).toInt()