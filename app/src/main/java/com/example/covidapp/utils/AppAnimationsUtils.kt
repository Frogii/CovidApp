package com.example.covidapp.utils


import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Animation

class AppAnimationsUtils {

    companion object {

        private const val ALPHA = "alpha"

        fun setBlinkAnimation(view: View) {
            ObjectAnimator.ofFloat(view, ALPHA, 0f, 1f).also {
                it.duration = 1200
                it.repeatMode = ValueAnimator.REVERSE
                it.repeatCount = Animation.INFINITE
                it.start()
            }
        }

        fun setFadeVisibility(view: View, visibility: Int) {
            if (visibility == View.VISIBLE) {
                ObjectAnimator.ofFloat(view, ALPHA, 0f, 1f).also {
                    it.duration = 300
                    it.start()
                }
            } else {
                ObjectAnimator.ofFloat(view, ALPHA, 1f, 0f).also {
                    it.duration = 300
                    it.start()
                }
            }
        }
    }
}