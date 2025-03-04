package com.example.fitsync.ui.editprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.R
import com.example.fitsync.data.ResultState
import com.example.fitsync.data.model.EditProfileRequest
import com.example.fitsync.databinding.ActivityEditProfileBinding
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.profile.ProfileActivity
import com.example.fitsync.utils.popupAlert

class EditProfileActivity : AppCompatActivity() {
   private var _binding: ActivityEditProfileBinding? = null
   private val binding get() = _binding!!

   private val viewModel by viewModels<EditProfileViewModel> {
      ViewModelFactory.getInstance(this)
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      _binding = ActivityEditProfileBinding.inflate(layoutInflater)
      setContentView(binding.root)
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         insets
      }

      window.statusBarColor = resources.getColor(R.color.white)

      val userId: String = intent.getStringExtra("userId")!!

      binding.btnKembali.setOnClickListener {
         val intent = Intent(this, ProfileActivity::class.java)
         startActivity(intent)
         finish()
      }
      binding.btnEdit.setOnClickListener {
         val firstName = binding.inputNamaDepan.text.toString()
         val lastName = binding.inputNamaBelakang.text.toString()
         val height = binding.inputTinggiBadan.text.toString()
         val weight = binding.inputBeratBadan.text.toString()
         if (firstName.isEmpty()) {
            binding.inputNamaDepan.error = getString(R.string.field_tidak_boleh_kosong)
            return@setOnClickListener
         }
         if (lastName.isEmpty()) {
            binding.inputNamaBelakang.error = getString(R.string.field_tidak_boleh_kosong)
            return@setOnClickListener
         }
         if (weight.isEmpty()) {
            binding.inputTinggiBadan.error = getString(R.string.field_tidak_boleh_kosong)
            return@setOnClickListener
         }
         if (height.isEmpty()) {
            binding.inputTinggiBadan.error = getString(R.string.minimal_password)
            return@setOnClickListener
         }
         val request = EditProfileRequest(
            firstName = firstName,
            lastName = lastName,
            height = height.toInt(),
            weight = weight.toInt(),
         )

         viewModel.editProfile(userId, request).observe(this@EditProfileActivity) { result ->
            when (result) {
               ResultState.Loading -> {
                  showLoading(true)
               }

               is ResultState.Error -> {
                  showLoading(false)
                  popupAlert(this, result.message)
               }

               is ResultState.Success -> {
                  showLoading(false)
                  popupAlert(this, result.data.message, onDismiss = {
                     val intent = Intent(this, ProfileActivity::class.java)
                     startActivity(intent)
                     finish()
                  })
               }
            }
         }
      }
   }

   private fun showLoading(isLoading: Boolean) {
      binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding =null
   }
}


