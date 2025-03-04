package com.example.fitsync.ui.pemula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.dadaPemula
import com.example.fitsync.data.gerakanlatihan.kaloriLatihan
import com.example.fitsync.data.gerakanlatihan.perutPemula
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.databinding.ActivityLatihanDadaPemulaBinding
import com.example.fitsync.databinding.ActivityLatihanOtotPerutPemulaBinding
import com.example.fitsync.ui.persiapanlatihan.PersiapanLatihanActivity

class LatihanDadaPemulaActivity : AppCompatActivity() {

   private var _binding: ActivityLatihanDadaPemulaBinding? = null
   private val binding get() = _binding!!
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      _binding = ActivityLatihanDadaPemulaBinding.inflate(layoutInflater)
      setContentView(binding.root)
      window.statusBarColor = getColor(R.color.navy)

      binding.btnBack.setOnClickListener { onBackPressed() }
      binding.btnStart.setOnClickListener {
         kaloriLatihan = 126
         bagianLatihan = "Dada"
         tingkatanLatihan = "Pemula"
         val intent = Intent(
            this@LatihanDadaPemulaActivity,
            PersiapanLatihanActivity::class.java
         )
         intent.putExtra("gerakan", dadaPemula)
         startActivity(intent)
      }

      Glide.with(this)
         .load(R.drawable.tricep_dips_gif)
         .centerCrop()
         .into(binding.imageTricpesDips)
      Glide.with(this)
         .load(R.drawable.cobra_stretch_gif)
         .centerCrop()
         .into(binding.imageCobraStrech)
      Glide.with(this)
         .load(R.drawable.diamond_push_up_gif)
         .centerCrop()
         .into(binding.imageDiamondPushup)
      Glide.with(this)
         .load(R.drawable.push_up_gif)
         .centerCrop()
         .into(binding.pushUpGif)
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}