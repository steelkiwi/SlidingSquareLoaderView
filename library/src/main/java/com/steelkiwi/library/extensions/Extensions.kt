package com.steelkiwi.library.extensions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.widget.RelativeLayout

/**
 * Created by yaroslav on 6/8/17.
 */

fun View.setLeftMargin(left: Int) {
    val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
    params.setMargins(left, 0, 0, 0)
    layoutParams = params
}

fun View.getLeftMargin() : Int {
    return (layoutParams as RelativeLayout.LayoutParams).leftMargin
}

inline fun ObjectAnimator.onEnd(crossinline func: () -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) { func() }
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })
}
