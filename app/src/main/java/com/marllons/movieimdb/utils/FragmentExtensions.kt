package com.marllons.movieimdb.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.setOnBackPressedListener(enabled: Boolean = true, event: () -> Unit) {
    activity?.onBackPressedDispatcher
        ?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                event()
            }
        })
}