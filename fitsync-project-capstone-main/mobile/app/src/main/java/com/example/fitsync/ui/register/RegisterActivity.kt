package com.example.fitsync.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.R
import com.example.fitsync.data.ResultState
import com.example.fitsync.databinding.ActivityOpeningBinding
import com.example.fitsync.databinding.ActivityRegisterBinding
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.login.LoginActivity
import com.example.fitsync.utils.popupAlert

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnKembali.setOnClickListener {
            onBackPressed()
            finish()
        }

        register()
    }

    private fun register() {
        binding.btnDaftar.setOnClickListener {
            val firsName = binding.etNamaDepan.text.toString()
            val lastName = binding.etNamaBelakang.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (firsName.isEmpty()) {
                binding.etNamaDepan.error = getString(R.string.field_tidak_boleh_kosong)
                return@setOnClickListener
            }
            if (lastName.isEmpty()) {
                binding.etNamaBelakang.error = getString(R.string.field_tidak_boleh_kosong)
                return@setOnClickListener
            }
            if (username.isEmpty()) {
                binding.etUsername.error = getString(R.string.field_tidak_boleh_kosong)
                return@setOnClickListener
            }
            if (password.length < 8) {
                binding.etPassword.error = getString(R.string.minimal_password)
                return@setOnClickListener
            }
            viewModel.register(firsName, lastName, username, password).observe(this) { result ->
                when (result) {
                    is ResultState.Loading -> {
                        showLoading(true)
                        buttonClickable(false)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        buttonClickable(true)
                        val response = result.data.message
                        popupAlert(this, response, onDismiss = {
                            startActivity(Intent(this, LoginActivity::class.java))
                        })
                    }

                    is ResultState.Error -> {
                        showLoading(false)
                        buttonClickable(true)
                        val errorMessage = result.message
                        popupAlert(this, errorMessage)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun buttonClickable(isTrue: Boolean) {
        binding.btnDaftar.isClickable = isTrue
        binding.etNamaDepan.isClickable = isTrue
        binding.etPassword.isClickable = isTrue
        binding.etNamaBelakang.isClickable = isTrue
        binding.etUsername.isClickable = isTrue

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progresLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

