package com.colleze.android.Views.Activities

import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.colleze.android.R

class OTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val relativeLayout = findViewById<RelativeLayout>(R.id.relativeLayout)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        relativeLayout.startAnimation(slideUp)

        val emailView = findViewById<TextView>(R.id.codeText)
        val builder = SpannableStringBuilder()

        // First part of the text
        val firstPart = "Code has been sent to "
        val firstSpan = ForegroundColorSpan(resources.getColor(R.color.black2))
        builder.append(firstPart, firstSpan, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Second part of the text
        val secondPart = "bh*********16@gmail.com"
        val secondSpan = ForegroundColorSpan(resources.getColor(R.color.blue))
        builder.append(secondPart, secondSpan, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        emailView.text = builder

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        handleOTP()
    }

    private fun handleOTP() {
        val otpEditTexts = arrayOf(
            findViewById<EditText>(R.id.code1),
            findViewById<EditText>(R.id.code2),
            findViewById<EditText>(R.id.code3),
            findViewById<EditText>(R.id.code4)
        )

        otpEditTexts.forEachIndexed { index, editText ->
            editText.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    editText.setBackgroundResource(R.drawable.rounded_corners)
                } else {
                    editText.setBackgroundResource(R.drawable.rounded_background)
                }
            }

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        editText.backgroundTintList = ContextCompat.getColorStateList(this@OTPActivity, R.color.blue)
                        if (index < otpEditTexts.size - 1) {
                            otpEditTexts[index + 1].requestFocus()
                        }
                    } else if (s?.length == 0) {
                        editText.backgroundTintList = ContextCompat.getColorStateList(this@OTPActivity, R.color.white2)
                        if (index > 0) {
                            otpEditTexts[index - 1].requestFocus()
                        }
                    }
                }
            })
        }

    }
}