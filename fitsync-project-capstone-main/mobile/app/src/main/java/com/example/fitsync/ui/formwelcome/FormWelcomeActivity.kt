package com.example.fitsync.ui.formwelcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.data.ResultState
import com.example.fitsync.databinding.ActivityFormWelcomeBinding
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.dashboard.MainActivity
import com.example.fitsync.utils.popupAlert

class FormWelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityFormWelcomeBinding? = null
    private val binding get() = _binding!!

    private var userId = ""

    private val viewModel by viewModels<FormWelcomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        _binding = ActivityFormWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getUserData()
        submitForm()
    }

    fun submitForm() {
        binding.btnKirim.setOnClickListener {
            val height = binding.etTinggiBadan.text.toString()
            val weight = binding.etBeratBadan.text.toString()
            viewModel.postBodyMetrik(userId, weight.toInt(), height.toInt())
                .observe(this) { result ->
                    when (result) {
                        ResultState.Loading -> {
                            showLoading(true)
                            buttonClickable(false)
                        }

                        is ResultState.Success -> {
                            showLoading(false)
                            buttonClickable(false)
                            val response = result.data.message
                            popupAlert(this, response, onDismiss = {
                                startActivity(Intent(this, MainActivity::class.java))
                            })
                        }

                        is ResultState.Error -> {
                            showLoading(false)
                            buttonClickable(true)
                            val errorMessage = result.message
                            popupAlert(this, errorMessage)
                        }
                    }
                }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progresLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun buttonClickable(isTrue: Boolean) {
        binding.btnKirim.isClickable = isTrue
        binding.etBeratBadan.isClickable = isTrue
        binding.etTinggiBadan.isClickable = isTrue
    }

    fun getUserData() {
        viewModel.getUserId().observe(this) { result ->
            userId = result
        }
    }
}