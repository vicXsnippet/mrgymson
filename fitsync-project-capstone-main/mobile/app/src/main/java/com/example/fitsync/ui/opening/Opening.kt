package com.example.fitsync.ui.opening

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.databinding.ActivityOpeningBinding
import com.example.fitsync.ui.login.LoginActivity

class Opening : AppCompatActivity() {
    private var _binding: ActivityOpeningBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityOpeningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val originalPaddingLeft = binding.main.paddingLeft
        val originalPaddingTop = binding.main.paddingTop
        val originalPaddingRight = binding.main.paddingRight
        val originalPaddingBottom = binding.main.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                originalPaddingLeft + systemBars.left,
                originalPaddingTop + systemBars.top,
                originalPaddingRight + systemBars.right,
                originalPaddingBottom + systemBars.bottom
            )
            insets
        }
        binding.btnMulai.setOnClickListener{
            startActivity(Intent(this@Opening, LoginActivity::class.java))
            finish()
        }                         
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}