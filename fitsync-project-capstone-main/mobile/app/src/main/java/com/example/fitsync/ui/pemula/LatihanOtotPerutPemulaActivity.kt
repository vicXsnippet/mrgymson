package com.example.fitsync.ui.pemula

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.kaloriLatihan
import com.example.fitsync.data.gerakanlatihan.perutPemula
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.databinding.ActivityLatihanOtotPerutPemulaBinding
import com.example.fitsync.ui.persiapanlatihan.PersiapanLatihanActivity

class LatihanOtotPerutPemulaActivity : AppCompatActivity() {
   private var _binding: ActivityLatihanOtotPerutPemulaBinding? = null
   private val binding get() = _binding!!
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      _binding = ActivityLatihanOtotPerutPemulaBinding.inflate(layoutInflater)
      setContentView(binding.root)

      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

         insets
      }

      window.statusBarColor = getColor(R.color.navy)

      binding.btnBack.setOnClickListener { onBackPressed() }
      binding.btnStart.setOnClickListener {
         kaloriLatihan = 126
         bagianLatihan = "Perut"
         tingkatanLatihan = "Pemula"
         val intent = Intent(
            this@LatihanOtotPerutPemulaActivity,
            PersiapanLatihanActivity::class.java
         )
         intent.putExtra("gerakan", perutPemula)
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