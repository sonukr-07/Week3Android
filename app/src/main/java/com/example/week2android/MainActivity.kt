package com.example.week2android

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.week2android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        // Initialize GestureDetector for double-tap detection
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                // Action for single tap
                _binding.textView.text = "Single Tap Detected"
                setImageToImg2()
                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                // Action for double tap
                _binding.textView.text = "Double Tap Detected"
                setImageUsingGlide()
                return true
            }
        })

        // Attach OnTouchListener to the button
        _binding.button.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    // Set ImageView to display img2 drawable
    private fun setImageToImg2() {
        val newImageDrawable = ContextCompat.getDrawable(this, R.drawable.image2)
        _binding.imageView.setImageDrawable(newImageDrawable)
    }

    // Set ImageView to display image using Glide
    private fun setImageUsingGlide() {
        Glide
            .with(this)
            .load("https://images.unsplash.com/photo-1565284475981-fca4b3a3627a?q=80&w=435&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
            .centerCrop()
            .into(_binding.imageView)
    }
}