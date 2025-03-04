package com.example.fitsync.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.R
import com.example.fitsync.data.ResultState
import com.example.fitsync.databinding.ActivityLoginBinding
import com.example.fitsync.ui.dashboard.MainActivity
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.formwelcome.FormWelcomeActivity
import com.example.fitsync.ui.register.RegisterActivity
import com.example.fitsync.utils.popupAlert

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnDaftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        login()
    }


    private fun login() {
        binding.btnMasuk.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (username.isEmpty()) {
                binding.etUsername.error = getString(R.string.field_tidak_boleh_kosong)
                return@setOnClickListener
            }
            if (password.length < 8) {
                binding.etPassword.error = getString(R.string.minimal_password)
                return@setOnClickListener
            }
            viewModel.login(username, password).observe(this) { result ->
                when (result) {
                    ResultState.Loading -> {
                        showLoading(true)
                        buttonClickable(false)
                    }

                    is ResultState.Success -> {
                        showLoading(false)
                        buttonClickable(true)
                        val response = result.data
                        userId = result.data.userId.toString()
                        popupAlert(this, response.message, "Login", onDismiss = {
                            getBM(userId)
                        })
                        viewModel.saveSession(response)
                    }

                    is ResultState.Error -> {
                        buttonClickable(true)
                        showLoading(false)
                        val response = result.message
                        popupAlert(this, response)
                    }
                }
            }
        }
    }

    private fun getBM(userId: String) {
        viewModel.getBodyMetrik(userId).observe(this) { result ->
            when (result) {
                ResultState.Loading -> {
                    showLoading(true)
                    buttonClickable(false)

                }

                is ResultState.Success -> {
                    showLoading(false)
                    buttonClickable(true)
                    val getWeight = result.data.beratBadan
                    val getHeight = result.data.tinggiBadan
                    if (getWeight != null && getHeight != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this, FormWelcomeActivity::class.java))
                        finish()
                    }
                }

                is ResultState.Error -> {
                    showLoading(true)
                    buttonClickable(true)
                    val response = result.message
                    popupAlert(this, response)
                }

            }

        }
    }

    private fun buttonClickable(isTrue: Boolean) {
        binding.btnDaftar.isClickable = isTrue
        binding.btnMasuk.isClickable = isTrue
        binding.etPassword.isClickable = isTrue
        binding.etUsername.isClickable = isTrue

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progresLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}