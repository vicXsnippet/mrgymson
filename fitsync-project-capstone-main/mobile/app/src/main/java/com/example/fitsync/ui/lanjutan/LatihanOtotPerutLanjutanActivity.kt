package com.example.fitsync.ui.lanjutan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.kaloriLatihan
import com.example.fitsync.data.gerakanlatihan.perutLanjutan
import com.example.fitsync.data.gerakanlatihan.perutPemula
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.databinding.ActivityLatihanOtotPerutLanjutanBinding
import com.example.fitsync.databinding.ActivityLatihanOtotPerutMenengahBinding
import com.example.fitsync.ui.persiapanlatihan.PersiapanLatihanActivity

class LatihanOtotPerutLanjutanActivity : AppCompatActivity() {
   private var _binding: ActivityLatihanOtotPerutLanjutanBinding? = null
   private val binding get() = _binding!!

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      _binding = ActivityLatihanOtotPerutLanjutanBinding.inflate(layoutInflater)
      setContentView(binding.root)

      window.statusBarColor = getColor(R.color.navy)

      binding.btnBack.setOnClickListener { onBackPressed() }
      binding.btnStart.setOnClickListener {
         kaloriLatihan = 326
         bagianLatihan = "Perut"
         tingkatanLatihan = "Lanjutan"
         val intent = Intent(
            this@LatihanOtotPerutLanjutanActivity,
            PersiapanLatihanActivity::class.java
         )
         intent.putExtra("gerakan", perutLanjutan)
         startActivity(intent)
      }

      Glide.with(this)
         .load(R.drawable.situp_gif)
         .centerCrop()
         .into(binding.imageSitup)
      Glide.with(this)
         .load(R.drawable.leg_raises_gif)
         .centerCrop()
         .into(binding.imageLegraises)
      Glide.with(this)
         .load(R.drawable.diamond_push_up_gif)
         .centerCrop()
         .into(binding.imageDiamondPushup)
      Glide.with(this)
         .load(R.drawable.cobra_stretch_gif)
         .centerCrop()
         .into(binding.imageCobrastretch)
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}