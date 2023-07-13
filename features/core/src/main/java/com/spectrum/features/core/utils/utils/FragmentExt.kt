package com.spectrum.features.core.utils.utils

import android.view.Gravity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


fun Fragment.showMessage(message: String?, duration: Int = 2000) {
    message ?: return
    val snackBar = Snackbar.make(requireView(), message, duration)
    val view = snackBar.view
    val params = view.layoutParams as? FrameLayout.LayoutParams ?: return
    params.gravity = Gravity.TOP
    view.layoutParams = params
    snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    snackBar.show()
}
