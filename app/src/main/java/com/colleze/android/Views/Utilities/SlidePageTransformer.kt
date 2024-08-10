package com.colleze.android.Views.Utilities

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class SlidePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.translationX = -position * view.width

        // Fade out the page as it moves further to the left
        view.alpha = 1 - Math.abs(position)

        // Optional: Scale the page down (between 0.85 and 1)
        val scaleFactor = 0.85f + (1 - Math.abs(position)) * 0.15f
        view.scaleX = scaleFactor
        view.scaleY = scaleFactor
    }
}
