package com.colleze.android.Views.Activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.colleze.android.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val collezeEditText = findViewById<EditText>(R.id.collezeId);
        val relativeLayout = findViewById<RelativeLayout>(R.id.relativeLayout)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        relativeLayout.startAnimation(slideUp)

        val colleges = listOf("Harvard University", "Stanford University", "Massachusetts Institute of Technology", "University of California, Berkeley", "Oxford University")
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, colleges)
        val collegeNameTextView = findViewById<AutoCompleteTextView>(R.id.collegeName)
        collegeNameTextView.setAdapter(adapter)
        collegeNameTextView.threshold = 1

        //add regex check
        val continueButton = findViewById<AppCompatButton>(R.id.btnContinue)
        continueButton.setOnClickListener {
            val collezeId = collezeEditText.text.toString()
            if (collezeId != "") {
                val intent = Intent(this, OTPActivity::class.java)
                intent.putExtra("COLLEZE_ID", collezeId);
                startActivity(intent)
            }
        }
    }
}