package com.colleze.android.Views.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.colleze.android.Adapters.LandingPagerAdapter
import com.colleze.android.R
import com.colleze.android.Views.Fragments.SlideFragment
import com.colleze.android.Views.Utilities.SlidePageTransformer

class LandingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var indicators: Array<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        indicatorLayout = findViewById(R.id.indicatorLinearLayout)
        viewPager = findViewById(R.id.viewPager)
        indicators = arrayOf(findViewById(R.id.step1), findViewById(R.id.step2), findViewById(R.id.step3))

        val fragments = listOf(
            SlideFragment.newInstance("College Essentials", "Stay informed with the latest college updates and announcements.", "notifications.json"),
            SlideFragment.newInstance("Results & Time Tables", "Access exam results and timetables in one convenient place.", "timetables.json"),
            SlideFragment.newInstance("Seamless Attendance", "Effortlessly track and manage your attendance.", "attendance.json")
        )

        Log.d("LandingActivity", "Fragment list size: ${fragments.size}")

        val adapter = LandingPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        val btnNext = findViewById<Button>(R.id.btnContinue)
        val btnSkip = findViewById<TextView>(R.id.btnSkip)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    btnNext.text = "Get Started !"
                } else {
                    btnNext.text = "Continue"
                }
                updateIndicators(position)
            }
        })

        btnNext.setOnClickListener {
            val nextItem = viewPager.currentItem + 1
            if (nextItem < adapter.itemCount) {
                viewPager.currentItem = nextItem
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        btnSkip.setOnClickListener {
            // Skip to the main screen or next activity
        }

        viewPager.setPageTransformer(SlidePageTransformer())
    }

    private fun updateIndicators(position: Int) {
        for (i in indicators.indices) {
            val params = indicators[i].layoutParams as LinearLayout.LayoutParams

            if (i == position) {
                indicators[i].setBackgroundResource(R.drawable.indicator_selected)
                params.width = 52
                params.height = 12
            } else {
                indicators[i].setBackgroundResource(R.drawable.indicator_default)
                params.width = 12
                params.height = 12
            }

            // Set the margin for all indicators
            params.setMargins(0, 0, 0, 0) // Left margin of 28dp
            indicators[i].layoutParams = params
        }
    }
}
