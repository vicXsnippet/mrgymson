package com.example.fitsync.ui.profile

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
import com.example.fitsync.databinding.ActivityProfileBinding
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.editprofile.EditProfileActivity
import com.google.android.material.snackbar.Snackbar

class ProfileActivity : AppCompatActivity() {
   private var _binding: ActivityProfileBinding? = null
   private val binding get() = _binding!!

   private val viewModel by viewModels<ProfileViewModel> {
      ViewModelFactory.getInstance(this)
   }

   private lateinit var _userId: String
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      enableEdgeToEdge()
      _binding = ActivityProfileBinding.inflate(layoutInflater)
      setContentView(binding.root)
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         insets
      }

      setSupportActionBar(binding.topAppBar)
      supportActionBar?.setDisplayShowTitleEnabled(false)

      binding.topAppBar.setNavigationOnClickListener {
         finish()
      }

      viewModel.getUserId().observe(this@ProfileActivity) { userId ->
         viewModel.getUserProfile(userId)
         _userId = userId
         viewModel.userData.observe(this@ProfileActivity) { result ->
            when (result) {
               is ResultState.Loading -> {
                  showLoading(true)
               }

               is ResultState.Success -> {
                  showLoading(false)
                  val data = result.data
                  binding.apply {
                     tvFirstName.text = data.firstName
                     tvLastName.text = data.lastName
                     tvHeight.text = getString(R.string._cm, String.format("%s", data.height))
                     tvWeight.text = getString(R.string._kg, String.format("%s", data.weight))
                  }
               }

               is ResultState.Error -> {
                  val snackbar = Snackbar.make(binding.root, result.message, Snackbar.LENGTH_LONG)
                  snackbar.setBackgroundTint(getColor(R.color.pink))
                  snackbar.setAction("Coba lagi") {
                     recreate()
                  }
                  snackbar.setActionTextColor(getColor(R.color.white))
                  snackbar.show()
               }
            }
         }
      }

      binding.btnEdit.setOnClickListener {
         val intent = Intent(this@ProfileActivity, EditProfileActivity::class.java)
         intent.putExtra("userId", _userId)
         startActivity(intent)
         finish()
      }
   }

   private fun showLoading(isLoading: Boolean) {
      binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}

