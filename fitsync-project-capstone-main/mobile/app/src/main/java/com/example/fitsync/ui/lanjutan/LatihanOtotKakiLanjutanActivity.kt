package com.example.fitsync.ui.lanjutan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.kakiLanjutan
import com.example.fitsync.data.gerakanlatihan.kaloriLatihan
import com.example.fitsync.data.gerakanlatihan.perutPemula
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.databinding.ActivityLatihanOtotKakiLanjutanBinding
import com.example.fitsync.ui.persiapanlatihan.PersiapanLatihanActivity

class LatihanOtotKakiLanjutanActivity : AppCompatActivity() {
   private var _binding: ActivityLatihanOtotKakiLanjutanBinding? = null
   private val binding get() = _binding!!

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      _binding = ActivityLatihanOtotKakiLanjutanBinding.inflate(layoutInflater)
      setContentView(binding.root)
      window.statusBarColor = getColor(R.color.navy)

      binding.btnBack.setOnClickListener { onBackPressed() }
      binding.btnStart.setOnClickListener {
         kaloriLatihan = 326
         bagianLatihan = "Kaki"
         tingkatanLatihan = "Lanjutan"
         val intent = Intent(
            this@LatihanOtotKakiLanjutanActivity,
            PersiapanLatihanActivity::class.java
         )
         intent.putExtra("gerakan", kakiLanjutan)
         startActivity(intent)
      }
      Glide.with(this)
         .load(R.drawable.squats_gif)
         .centerCrop()
         .into(binding.imageSquats)
      Glide.with(this)
         .load(R.drawable.jumping_jacks_gif)
         .centerCrop()
         .into(binding.imageJumpingJack)
      Glide.with(this)
         .load(R.drawable.leg_raises_gif)
         .centerCrop()
         .into(binding.imageLegRaises)
      Glide.with(this)
         .load(R.drawable.jumping_squats_gif)
         .centerCrop()
         .into(binding.imageJumpingSquats)
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}